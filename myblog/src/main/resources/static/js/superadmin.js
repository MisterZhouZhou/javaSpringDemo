
    var deleteConfig={};

    $('.superAdminList .superAdminClick').click(function () {
        var flag = $(this).attr('class').substring(16);
        $('#statistics,#articleManagement,#articleComment,#articleCategories,#bannerManagement,#friendLink,#userFeedback,#privateWord,#expressInfo,#excelInfo,#carInfo,#bannerAdd').css("display","none");
        $("#" + flag).css("display","block");
    });

    // 失败消息盒
    function dangerNotice(notice) {
        $('.dangerNotice').html(notice);
        $('.dangerNoticeAlert').css("display","block");
        var closeNoticeBox = setTimeout(function () {
            $('.dangerNoticeAlert').css("display","none");
        },3000);
    }
    // 成功消息盒
    function successNotice(notice) {
        $('.successNotice').html(notice);
        $('.successNoticeAlert').css("display","block");
        var closeNoticeBox = setTimeout(function () {
            $('.successNoticeAlert').css("display","none");
        },3000);
    }

    //填充悄悄话
    function putInAllPrivateWord(data) {
        var privateWord = $('.superAdminInfo .privateWord');
        privateWord.empty();
        var amPanelGroup = $('<div class="am-panel-group" id="accordion"></div>');
        $.each(data['result'], function (index,obj) {
            var amPanel = $('<div class="am-panel am-panel-default"></div>');
            amPanel.append('<div class="am-panel-hd">' +
                '<h4 style="font-weight: 500" class="am-panel-title" data-am-collapse="{parent: \'#accordion\', target: \'#do-not-say-' + index + '\'}">' +
                obj['publisher'] +
                '</h4>' +
                '</div>');
            var doNotSay = $('<div id="do-not-say-' + index + '" class="am-panel-collapse am-collapse"></div>');
            var userPrivateWord = $('<div class="userPrivateWord am-panel-bd"></div>');
            var userPrivateWordUl = $('<ul class="am-list am-list-border"></ul>');
            $.each(obj['content'], function (index1, obj1) {
                if(obj1['replyContent'] !== ""){
                    userPrivateWordUl.append('<li>' +
                        '<div class="userPrivateWordTime">' +
                        obj1['publisherDate'] +
                        '</div><br>' +
                        '<a id="p' + obj1['id'] + '">' + obj1['privateWord']+
                        '<br>' +
                        '<div class="myReply">' +
                        '回复：<span class="myReplyContent">' + obj1['replyContent'] + '</span>' +
                        '</div></a>' +
                        '</li>');
                } else {
                    userPrivateWordUl.append('<li>' +
                        '<div class="userPrivateWordTime">' +
                        obj1['publisherDate'] +
                        '</div><br>' +
                        '<a id="p' + obj1['id'] + '">' + obj1['privateWord']+
                        '<br>' +
                        '<div class="myNoReply">' +
                        '回复：<span class="myReplyContent">还没有回复人家哦</span>' +
                        '</div></a><div class="userPrivateWordReply am-animation-slide-top">' +
                        '<textarea class="replyTextarea" placeholder="填写悄悄话回复"></textarea>' +
                        '<button type="button" class="userPrivateWordReplyBtn am-btn am-btn-success am-round">回复</button>' +
                        '<button type="button" class="userPrivateWordReplyCloseBtn am-btn am-round">取消</button>' +
                        '</div>' +
                        '</li>');
                }
            });
            userPrivateWord.append(userPrivateWordUl);
            doNotSay.append(userPrivateWord);
            amPanel.append(doNotSay);
            amPanelGroup.append(amPanel);
        });
        privateWord.append(amPanelGroup);

        $('.userPrivateWord a').click(function () {
            var $this = $(this);
            var userPrivateWordReply = $this.next();
            userPrivateWordReply.toggle();
        });
        $('.userPrivateWordReplyCloseBtn').click(function () {
            $('.userPrivateWordReplyCloseBtn').parent().css("display","none");
        });

        $('.userPrivateWordReplyBtn').click(function () {
           var $this = $(this);
           var replyId = $this.parent().prev().attr("id").substring(1);
           var textarea = $this.prev().val();
           if(textarea.length == 0){
               dangerNotice("你还没有填写回复内容！")
           } else {
               $.ajax({
                   type:'post',
                   url:'/replyPrivateWord',
                   dataType:'json',
                   data:{
                       replyId:replyId,
                       replyContent:textarea
                   },
                   success:function (data) {
                       if(data['status'] == 403){
                           $.get("/toLogin",function(data,status,xhr){
                               window.location.replace("/login");
                           });
                       } else {
                           successNotice("回复成功！");
                           $this.prev().val("");
                           $('#p' + data['result']['replyId']).find('.myReplyContent').html(data['result']['replyContent']);
                           $this.parent().css("display","none");
                           $this.parent().prev().find('.myNoReply').css("color","#b5b5b5");
                           $this.parent().prev().attr('disabled', 'true');
                       }
                   },
                   error:function () {
                       alert("获取悄悄话失败");
                   }
               });
           }
        });
    }
    //填充反馈信息
    function putInAllFeedback(data) {
        var feedbackInfos = $('.feedbackInfos');
        feedbackInfos.empty();
        if(data['result'].length == 0){
            feedbackInfos.append('<div class="noFeedback">无反馈信息</div>');
        } else {
            $.each(data['result'], function (index, obj) {
                var feedbackInfo = $('<div class="feedbackInfo"></div>');
                feedbackInfo.append('<div class="feedbackInfoTitle">' +
                    '<span class="feedbackName">' + obj['person'] + '</span>' +
                    '<span class="feedbackTime">' + obj['feedbackDate'] + '</span>' +
                    '</div>');
                feedbackInfo.append('<div class="feedbackInfoContent">' +
                    '<span class="feedbackInfoContentWord">反馈内容：</span>' +
                    obj['feedbackContent'] +
                    '</div>');
                var feedbackInfoContact = $('<div class="feedbackInfoContact"></div>');
                if(obj['contactInfo'] !== ""){
                    feedbackInfoContact.append('<span class="contactInfo">联系方式：</span>' +
                        obj['contactInfo']);
                } else {
                    feedbackInfoContact.append('<span class="contactInfo">联系方式：</span>' + '无'
                    );
                }
                feedbackInfo.append(feedbackInfoContact);
                feedbackInfos.append(feedbackInfo);
            });
            feedbackInfos.append($('<div class="my-row" id="page-father">' +
                '<div id="feedbackPagination">' +
                '<ul class="am-pagination  am-pagination-centered">' +
                '<li class="am-disabled"><a href="">&laquo; 上一页</a></li>' +
                '<li class="am-active"><a href="">1</a></li>' +
                '<li><a href="">2</a></li>' +
                '<li><a href="">3</a></li>' +
                '<li><a href="">4</a></li>' +
                '<li><a href="">5</a></li>' +
                '<li><a href="">下一页 &raquo;</a></li>' +
                '</ul>' +
                '</div>' +
                '</div>'));
        }

    }
    //填充文章管理
    function putInArticleManagement(data) {
        var articleManagementTable = $('.articleManagementTable');
        articleManagementTable.empty();
        $.each(data['result'], function (index, obj) {
            articleManagementTable.append($('<tr id="a' + obj['id'] + '"><td><a href="findArticle?articleId=' + obj['articleId'] + '&originalAuthor=' + obj['originalAuthor'] + '">' + obj['articleTitle'] + '</a></td><td>' + obj['publishDate'] + '</td><td>' + obj['articleCategories'] + '</td> <td><span class="am-badge am-badge-success">' + obj['visitorNum'] + '</span></td>' +
                '<td>' +
                '<div class="am-dropdown" data-am-dropdown>' +
                '<button class="articleManagementBtn articleEditor am-btn am-btn-secondary">编辑</button>' +
                '<button class="articleDeleteBtn articleDelete am-btn am-btn-danger">删除</button>' +
                '</div>' +
                '</td>' +
                '</tr>'));
        });
        articleManagementTable.append($('<div class="my-row" id="page-father">' +
            '<div id="articleManagementPagination">' +
            '<ul class="am-pagination  am-pagination-centered">' +
            '</ul>' +
            '</div>' +
            '</div>'));

        $('.articleManagementBtn').click(function () {
           var $this = $(this);
           var id = $this.parent().parent().parent().attr("id").substring(1);
           window.location.replace("/editor?id=" + id);
        });
        $('.articleDeleteBtn').click(function () {
            var $this = $(this);
            var deleteArticleId = $this.parent().parent().parent().attr("id").substring(1);
            deleteConfig = {type: 'deleteArticle', deleteId: deleteArticleId};
            $('#deleteAlter').modal('open');
        })
    }

    //填充评论内容
    function putInArticleCommnet(data) {
        var articleCommentTable = $('.articleCommentTable');
        articleCommentTable.empty();
        $.each(data['result'], function (index, obj) {
            $.each(obj['comments'], function (cindex, cobj) {
                var commentContent = cobj['commentAnswer'] + '评论 ' + cobj['responseAnswer'] + '：' + cobj['commentContent'];
                if(cobj['commentAnswerId'] == cobj['responseAnswerId']){ // 评论人和回应者是同一个人
                    commentContent = cobj['commentAnswer'] + '发表评论：' + cobj['commentContent'];
                }
                articleCommentTable.append($('<tr id="a' + cobj['id'] + '"><td><a href="findArticle?articleId=' + obj['articleId'] + '&originalAuthor=' + obj['originalAuthor'] + '">' + obj['articleTitle'] + '</a></td><td>' + obj['publishDate'] + '</td><td>' + obj['articleCategories'] + '</td> <td><span class="am-badge am-badge-success">' + commentContent  + '</span></td>' +
                    '<td>' +
                    '<div class="am-dropdown" data-am-dropdown>' +
                    '<button class="commentDeleteBtn articleDelete am-btn am-btn-danger">删除</button>' +
                    '</div>' +
                    '</td>' +
                    '</tr>'));
            });
        });
        articleCommentTable.append($('<div class="my-row" id="page-father">' +
            '<div id="articleCommnetPagination">' +
            '<ul class="am-pagination  am-pagination-centered">' +
            '</ul>' +
            '</div>' +
            '</div>'));
        $('.commentDeleteBtn').click(function () {
            var $this = $(this);
            var deleteCommentId = $this.parent().parent().parent().attr("id").substring(1);
            deleteConfig = {type: 'deleteComment', deleteId: deleteCommentId};
            $('#deleteAlter').modal('open');
        })
    }

    //填充文章分类管理
    function putInArticleCategories(data) {
        var articleCategoriesTable = $('.articleCategoriesTable');
        articleCategoriesTable.empty();
        $.each(data['result'], function (index, obj) {
            articleCategoriesTable.append($('<tr id="a' + obj['categoryId'] + '"><td>' + obj['categoryName'] + '</td> <td><span class="am-badge am-badge-success">' + obj['categoryArticleNum'] + '</span></td>' +
                '<td>' +
                '<div class="am-dropdown" data-am-dropdown>' +
                //'<button class="articleManagementBtn articleEditor am-btn am-btn-secondary">编辑</button>' +
                '<button class="categoryDeleteBtn articleDelete am-btn am-btn-danger">删除</button>' +
                '</div>' +
                '</td>' +
                '</tr>'));
        });
        articleCategoriesTable.append($('<div class="my-row" id="page-father">' +
            '<div id="articleCategoriesPagination">' +
            '<ul class="am-pagination  am-pagination-centered">' +
            '</ul>' +
            '</div>' +
            '</div>'));

        $('.categoryDeleteBtn').click(function () {
            var $this = $(this);
            var deleteCategoryId = $this.parent().parent().parent().attr("id").substring(1);
            deleteConfig = {type: 'deleteCategory', deleteId: deleteCategoryId};
            $('#deleteAlter').modal('open');
        })
    }

    // 填充轮播信息
    function putInBanner(data) {
        var bannerManagementTable = $('.bannerManagementTable');
        bannerManagementTable.empty();
        $.each(data['result'], function (index, obj) {
            var operationTitle = obj['show'] ? '不显示' : '显示';
            bannerManagementTable.append($('<tr id="a' + obj['id'] + '"><td>' + obj['bannerName'] + '</td>' +
                '<td>' +
                '<div class="am-dropdown" data-am-dropdown>' +
                '<button class="bannerShowHideBtn am-btn am-btn-danger">'+ operationTitle +'</button>' +
                '</div>' +
                '</td>' +
                '</tr>'));
        });
        // bannerManagementTable.append($('<div class="my-row" id="page-father">' +
        //     '<div id="bannerManagementPagination">' +
        //     '<ul class="am-pagination  am-pagination-centered">' +
        //     '</ul>' +
        //     '</div>' +
        //     '</div>'));


        $('.bannerShowHideBtn').click(function () {
            var $this = $(this);
            var bannerId = $this.parent().parent().parent().attr("id").substring(1);
            // 更改后的状态值
            var isShow = this.innerText == '显示' ? 1: 0;
            updateBanner(bannerId, isShow);
        });
    }

    //填充快递进程
    function putInExpressInfo(data){
        var categoryTimeline = $('.superAdminInfo .expressSite');
        categoryTimeline.empty();
        var timeline = $('<div class="timeline timeline-wrap"></div>');
        timeline.append('<div class="timeline-row">' +
            '<span class="node" style="-webkit-box-sizing: content-box;-moz-box-sizing: content-box;box-sizing: content-box;">' +
            '<i class="fa fa-calendar"></i>' +
            '</span>' +
            '<h1 class="title  am-animation-slide-top"># 快递邮递进程 #</h1>' +
            '</div>');
        $.each(data['result'], function (index, obj) {
            // timeline.append($('<div class="timeline-row-major">' +
            //     '<span class="node am-animation-slide-top am-animation-delay-1"></span>' +
            //     '<div class="nodeYear am-animation-slide-top am-animation-delay-1">' + obj['time'] + '</div>' +
            //     '</div>'));
            var timelineRowMajor = $('<div class="timeline-row-major"></div>');
            timelineRowMajor.append($('<span class="node am-animation-slide-top am-animation-delay-1"></span>'));
            var content = $('<div class="content am-comment-main am-animation-slide-top am-animation-delay-1"></div>');
            content.append($('<header class="am-comment-hd" style="background: #fff">' +
                '<div class="contentTitle am-comment-meta">' +
                '<a>' + obj['time'] + '</a>' +
                '</div>' +
                '</header>'));
            var amCommentBd = $('<div class="am-comment-bd"><p>'+ obj['context'] +'</p></div>');
            content.append(amCommentBd);
            timelineRowMajor.append(content);
            timeline.append(timelineRowMajor);
        });
        categoryTimeline.append(timeline);
    }


    //填充excel信息
    function putInExcelProducts(data) {
        var excelsTable = $('.excelsTable');
        excelsTable.empty();
        $.each(data['data'], function (index, obj) {
            excelsTable.append($('<tr><td>' + obj['name'] + '</td><td>' + obj['price'] + '</td> <td><span class="am-badge am-badge-success">' + obj['date']  + '</span></td></tr>'));
        });
        excelsTable.append($('<div class="my-row" id="page-father">' +
            '<div id="excelsPagination">' +
            '<ul class="am-pagination  am-pagination-centered">' +
            '</ul>' +
            '</div>' +
            '</div>'));
    }

    //填充车辆信息
    function putInCarInfo(data) {
        var carUlComponent = $('.car-list');
        carUlComponent.empty();
        $.each(data, function (index, carCategory) {
            var itemList='';
            $.each(carCategory['cars'], function (index, carInfo) {
                itemList +='<li>' +
                        '<div>' +
                            '<span style="margin-left: 10px;">车辆名称：'+carInfo['carName']+'</span>'+
                            '<span style="margin-left: 10px;">车辆长度：'+carInfo['carLength']+'</span>'+
                            '<span style="margin-left: 10px;">车辆颜色：'+carInfo['carColor']+'</span>'+
                            '<span style="margin-left: 10px;">车辆价格范围：'+carInfo['carPrice']+'</span>'+
                        '</div>' +
                    '</li>';

            });

            carUlComponent.append($(
                '<li class="admin-parent">'+
                    '<a class="am-cf" data-am-collapse="{target: \'#car-nav'+index+'\'}">'+
                        '<div class="car-item">'+
                            '<img src="'+carCategory['carCategoryIcon']+'" />'+
                            '<span style="margin-left: 10px;">'+carCategory['carCategoryName']+'</span>'+
                            '<span style="margin-left: 10px;">'+carCategory['carCategoryPrice']+'</span>'+
                            '<span class="am-icon-angle-right am-fr am-margin-right car-arrow"></span>'+
                        '</div>'+
                    '</a>'+
                    '<ul class="am-list am-collapse admin-sidebar-sub" id="car-nav'+index+'">'+ itemList +'</ul>'+
                '</li>'
            ));
        });
        carUlComponent.append($('<div class="my-row" id="page-father">' +
            '<div id="carInfoPagination">' +
            '<ul class="am-pagination  am-pagination-centered">' +
            '</ul>' +
            '</div>' +
            '</div>'));
    }

    $('.sureArticleDeleteBtn').click(function () {
        if(deleteConfig){
            if(deleteConfig['type'] == 'deleteArticle'){ // 删除文章
                deleteArticle(deleteConfig['deleteId']);
            }else if(deleteConfig['type'] == 'deleteComment'){ // 删除评论
                deleteComment(deleteConfig['deleteId']);
            }else if(deleteConfig['type'] == 'deleteCategory'){ // 删除文章分类
                deleteCategory(deleteConfig['deleteId']);
            }
        }
    });

    // ---------------------------------------------- 接口 ----------------------------------------------
    // 更新banner信息
    function updateBanner(bannerId, isShow) {
        $.ajax({
            type:'post',
            url:'/updateBanner',
            contentType: "application/json",
            data:JSON.stringify({
                id:bannerId,
                show: isShow
            }),
            success:function (data) {
                if(data == 0){
                    alert('更新banner状态失败');
                } else {
                    // 刷新状态
                    getBannerManagement();
                }
            },
            error:function (error) {
                alert("更新banner状态失败");
            }
        });
    }

    // 删除文章方法
    function deleteArticle(articleId) {
        $.ajax({
            type:'get',
            url:'/deleteArticle',
            dataType:'json',
            data:{
                id:articleId
            },
            success:function (data) {
                if(data == 0){
                    dangerNotice("删除文章失败")
                } else {
                    successNotice("删除文章成功");
                    getArticleComment(1);
                }
            },
            error:function () {
                alert("删除失败");
            }
        });
    }

    // 删除评论方法
    function deleteComment(commentId) {
        $.ajax({
            type:'get',
            url:'/deleteComment',
            dataType:'json',
            data:{
                id:commentId
            },
            success:function (data) {
                if(data == 0){
                    dangerNotice("删除评论失败")
                } else {
                    successNotice("删除评论成功");
                    getArticleManagement(1);
                }
            },
            error:function () {
                alert("删除失败");
            }
        });
    }

    // 删除文章分类
    function deleteCategory(categoryId) {
        $.ajax({
            type:'get',
            url:'/deleteCategory',
            dataType:'json',
            data:{
                id:categoryId
            },
            success:function (data) {
                if(data == 0){
                    dangerNotice("删除文章分类失败")
                } else {
                    successNotice("删除文章分类成功");
                    getArticleManagement(1);
                }
            },
            error:function () {
                alert("删除失败");
            }
        });
    }

    //获得反馈信息
    function getAllFeedback(currentPage) {
        $.ajax({
            type:'get',
            url:'/getAllFeedback',
            dataType:'json',
            data:{
                rows:10,
                pageNum:currentPage
            },
            success:function (data) {
                putInAllFeedback(data);
                scrollTo(0,0);//回到顶部

                //分页
                $("#feedbackPagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        getAllFeedback(currentPage);
                    }
                });
            },
            error:function () {
                alert("获取反馈信息失败");
            }
        });
    }

    //获取统计信息
    function getStatisticsInfo() {
        $.ajax({
            type:'get',
            url:'/getStatisticsInfo',
            dataType:'json',
            data:{
            },
            success:function (data) {
                $('.allVisitor').html(data['allVisitor']);
                $('.yesterdayVisitor').html(data['yesterdayVisitor']);
                $('.allUser').html(data['allUser']);
                $('.articleNum').html(data['articleNum']);
                $('#commentItem').html(data['commentNum']);
            },
            error:function () {
                alert("获取统计信息失败");
            }
        });
    }

    //获得文章管理文章
    function getArticleManagement(currentPage) {
        $.ajax({
            type:'get',
            url:'/getArticleManagement',
            dataType:'json',
            data:{
                rows:10,
                pageNum:currentPage
            },
            success:function (data) {
                putInArticleManagement(data);
                scrollTo(0,0);//回到顶部

                //分页
                $("#articleManagementPagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        getArticleManagement(currentPage);
                    }
                });
            },
            error:function () {
                alert("获取文章信息失败");
            }
        });
    }

    //获得评论管理
    function getArticleComment(currentPage) {
        $.ajax({
            type:'get',
            url:'/getArticleComment',
            dataType:'json',
            data:{
                rows:10,
                pageNum:currentPage
            },
            success:function (data) {
                // 填充页面信息
                putInArticleCommnet(data);
                scrollTo(0,0);//回到顶部

                //分页
                $("#articleCommnetPagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        getArticleManagement(currentPage);
                    }
                });
            },
            error:function () {
                alert("获取文章信息失败");
            }
        });
    }

    // 获取文章分类信息
    function getArticleCategoriesInfo(currentPage) {
        $.ajax({
            type:'get',
            url:'/getArticleCategories',
            dataType:'json',
            data:{
                rows:10,
                pageNum:currentPage
            },
            success:function (data) {
                // 填充页面信息
                putInArticleCategories(data);
                scrollTo(0,0);//回到顶部

                //分页
                $("#articleCategoriesPagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        getArticleCategoriesInfo(currentPage);
                    }
                });
            },
            error:function () {
                alert("获取文章信息失败");
            }
        });
    }

    //获得轮播消息信息
    function getBannerManagement() {
        $.ajax({
            type:'get',
            url:'/getBanners',
            dataType:'json',
            // data:{
            //     rows:10,
            //     pageNum:currentPage
            // },
            success:function (data) {
                // 填充页面信息
                putInBanner(data);
                scrollTo(0,0);//回到顶部

                // //分页
                // $("#articleCategoriesPagination").paging({
                //     rows:data['pageInfo']['pageSize'],//每页显示条数
                //     pageNum:data['pageInfo']['pageNum'],//当前所在页码
                //     pages:data['pageInfo']['pages'],//总页数
                //     total:data['pageInfo']['total'],//总记录数
                //     callback:function(currentPage){
                //         getArticleCategoriesInfo(currentPage);
                //     }
                // });
            },
            error:function () {
                alert("获取轮播信息失败");
            }
        });
    }

    //获得快递信息
    function getExpressInfo(logisticsCode,logisticsNo) {
        $.ajax({
            type:'get',
            url:'/getExpressInfo',
            dataType:'json',
            data:{
                logisticsCode:logisticsCode,
                logisticsNo:logisticsNo
            },
            success:function (data) {
                putInExpressInfo(data);
                scrollTo(0,0);//回到顶部
            },
            error:function () {
                alert("获取快递信息失败");
            }
        });
    }

    // 添加文章分类
    function addCategoryInfo(categoryName){
        $.ajax({
            type: 'post',
            url: '/addCategory',
            //dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "categoryName":categoryName
            }),
            success: function (data) {
                if(data['code'] == 200){
                    successNotice("添加文章类别成功");
                    getArticleComment(1);
                }else{
                    dangerNotice("添加文章类别失败")
                }
            },
            error: function (e) {
                alert("fail: "+e.toString());
            }
        });
    }

    // 添加banner
    function addBannerInfo(bannerName){
        $.ajax({
            type: 'post',
            url: '/addBanner',
            contentType: "application/json",
            data: JSON.stringify({
                "bannerName":bannerName
            }),
            success: function (data) {
                if(data['code'] == 200){
                    successNotice("添加文章类别成功");
                    getArticleComment(1);
                }else{
                    dangerNotice("添加文章类别失败")
                }
            },
            error: function (e) {
                alert("fail: "+e.toString());
            }
        });
    }

    // 获取excel信息
    function getExcelInfo(currentPage) {
        $.ajax({
            type:'get',
            url:'/excel/getProducts',
            dataType:'json',
            data:{
                rows:10,
                pageNum:currentPage
            },
            success:function (result) {
                var data = result['result'];
                // 填充页面信息
                putInExcelProducts(data);
                scrollTo(0,0);//回到顶部

                //分页
                $("#excelsPagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        getExcelInfo(currentPage);
                    }
                });
            },
            error:function () {
                alert("获取excel信息失败");
            }
        });
    }

    // 获取车辆信息
    function getCarInfo(currentPage) {
        $.ajax({
            type:'get',
            url:'/carInfo/getCarsInfos',
            dataType:'json',
            data:{
                rows:10,
                pageNum:currentPage
            },
            success:function (result) {
                var data = result['result'];
                // 填充页面信息
                putInCarInfo(data['data']);
                scrollTo(0,0);//回到顶部

                //分页
                $("#carInfoPagination").paging({
                    rows:data['pageInfo']['pageSize'],//每页显示条数
                    pageNum:data['pageInfo']['pageNum'],//当前所在页码
                    pages:data['pageInfo']['pages'],//总页数
                    total:data['pageInfo']['total'],//总记录数
                    callback:function(currentPage){
                        getCarInfo(currentPage);
                    }
                });
            },
            error:function () {
                alert("获取车辆信息失败");
            }
        });
    }

    // ---------------------------------------------- 左侧入口点击事件 ----------------------------------------------
    //点击悄悄话
    $('.superAdminList .privateWord').click(function () {
        $.ajax({
            type:'post',
            url:'/getAllPrivateWord',
            dataType:'json',
            data:{
            },
            success:function (data) {
                if(data['result'].length == 0){
                    $('.privateWord').append($('<div>无悄悄话</div>'));
                } else {
                    putInAllPrivateWord(data);
                }
            },
            error:function () {
                alert("获取悄悄话失败");
            }
        });
    });

    //点击反馈
    $('.superAdminList .userFeedback').click(function () {
        getAllFeedback(1);
    });

    //点击文章管理
    $('.superAdminList .articleManagement').click(function () {
        getArticleManagement(1);
    });

    //点击评论管理
    $('.superAdminList .articleComment').click(function () {
        getArticleComment(1);
    });

    //点击文章分类管理
    $('.superAdminList .articleCategories').click(function () {
        getArticleCategoriesInfo(1);
    });

    //点击轮播消息管理
    $('.superAdminList .bannerManagement').click(function () {
        getBannerManagement(1);
    });

    // excel 入口点击事件
    $('.superAdminList .excelInfo').click(function () {
        getExcelInfo(1);
    });

    // 车辆信息 入口点击事件
    $('.superAdminList .carInfo').click(function () {
        getCarInfo(1);
    });

    // ---------------------------------------------- 右侧点击事件 ----------------------------------------------
    // 添加文章分类
    $('#addCategory_btn').click(function () {
        var category = $('#category_name');
        addCategoryInfo($.trim(category.val()));
    });

    // 添加banner
    $('#addBanner_btn').click(function () {
        var bannerName = $('#banner_name');
        addBannerInfo($.trim(bannerName.val()));
    });


    //快递订单，点击查询
    $('#expressSearch').click(function () {
        var logisticsCode = $('#expressInfo #logisticsCode');
        var logisticsNo = $('#expressInfo #logisticsNo');
        getExpressInfo($.trim(logisticsCode.val()), $.trim(logisticsNo.val()));
    });

    getStatisticsInfo();

    //图片懒加载 注意: 需要引入jQuery和underscore
    $(function() {
        // 获取window的引用:
        var $window = $(window);
        // 获取包含data-src属性的img，并以jQuery对象存入数组:
        var lazyImgs = _.map($('img[data-src]').get(), function (i) {
            return $(i);
        });
        // 定义事件函数:
        var onScroll = function () {
            // 获取页面滚动的高度:
            var wtop = $window.scrollTop();
            // 判断是否还有未加载的img:
            if (lazyImgs.length > 0) {
                // 获取可视区域高度:
                var wheight = $window.height();
                // 存放待删除的索引:
                var loadedIndex = [];
                // 循环处理数组的每个img元素:
                _.each(lazyImgs, function ($i, index) {
                    // 判断是否在可视范围内:
                    if ($i.offset().top - wtop < wheight) {
                        // 设置src属性:
                        $i.attr('src', $i.attr('data-src'));
                        // 添加到待删除数组:
                        loadedIndex.unshift(index);
                    }
                });
                // 删除已处理的对象:
                _.each(loadedIndex, function (index) {
                    lazyImgs.splice(index, 1);
                });
            }
        };
        // 绑定事件:
        $window.scroll(onScroll);
        // 手动触发一次:
        onScroll();
    });