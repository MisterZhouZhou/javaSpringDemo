function captchaReload() {
    $.ajax( {
        url: '/registration/captchareload/',
        type: 'get',
        success: function( responseText ) {
            if( responseText ) {
                $( '#captcha' ).html( '' ).html( responseText );
            }
        }
    } );
}

function login() {
    $('form').submit();
}