$(function(){
    $("#selectCityId").change(function(){
        var cityId=$("#selectCityId").val();
        var url='/weather/city/'+cityId;
        window.location.href=url;
    })
});