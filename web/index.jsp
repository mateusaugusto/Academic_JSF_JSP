<%-- 
    Document   : index
    Created on : 23/05/2014, 13:31:56
    Author     : Mateus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (request.getSession() != null) {
        Object userType = request.getSession().getAttribute("userType");
        
        if (userType != null && userType.toString().equals("admin")) {
            response.sendRedirect("Adm/index.jsp");
        }
    }
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Autenticação</title>

        <!-- Stylesheets -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/animation.css" rel="stylesheet">
        <link href="css/checkbox/orange.css" rel="stylesheet">
        <link href="css/preview.css" rel="stylesheet">
        <link href="css/authenty.css" rel="stylesheet">

        <!-- Font Awesome CDN -->
        <link href="css/font-awesome.css" rel="stylesheet">
    </head>
    <body>
        <section id="authenty_preview">
            <section id="signin_main" class="authenty signin-main">
                <div class="section-content">
                    <div class="wrap">
                        <div class="container" style="margin-top:-100px">
                            <div class="form-wrap">
                                <div class="row">
                                    <div class="title" data-animation="fadeInDown" data-animation-delay=".8s" style="margin-top:0">
                                        <h1>Autenticação</h1>
                                        <h5>Autenticação painel academico</h5>
                                    </div>
                                    <div id="form_1" data-animation="bounceIn">
                                        <div class="form-header" style="margin-top:-5%"> <i class="fa fa-user"></i> </div>
                                        <div class="form-main">
                                            <form action="src/autentica.jsp" method="GET">
                                                <div class="form-group">
                                                    <input name="login" type="text" id="un_1" class="form-control" placeholder="Matrícula" required>
                                                    <input name="senha" type="password" id="pw_1" class="form-control" placeholder="Senha" required>
                                                    <select name="tipo" class="form-control">
                                                        <option value="administrador">Administrador</option>
                                                        <option value="professor">Professor</option>
                                                        <option value="aluno">Aluno</option>
                                                        
                                                    </select>
                                                </div>
                                                <!--<button id="signIn_1" type="submit" class="btn btn-block signin"  onclick="location.href = 'Adm/index.html'">Registre-se</button>-->
                                                <input name="submit" value="Login" type="submit" class="btn btn-block signin" />

                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>

        <!-- js library --> 
        <script src="js/preview/jquery.min.js"></script> 
        <script src="js/preview/jquery-ui.min.js"></script> 
        <script src="js/bootstrap.min.js"></script> 
        <script src="js/jquery.icheck.min.js"></script> 
        <script src="js/waypoints.min.js"></script> 

        <!-- authenty js --> 
        <script src="js/authenty.js"></script> 

        <!-- preview scripts --> 
        <script src="js/preview/jquery.malihu.PageScroll2id.js"></script> 
        <script src="js/preview/jquery.address-1.6.min.js"></script> 
        <script src="js/preview/scrollTo.min.js"></script> 
        <script src="js/preview/init.js"></script> 

        <!-- preview scripts --> 
        <script>
                                                    (function($) {

                                                        // get full window size
                                                        $(window).on('load resize', function() {
                                                            var w = $(window).width();
                                                            var h = $(window).height();

                                                            $('section').height(h);
                                                        });

                                                        // scrollTo plugin
                                                        $('#signup_from_1').scrollTo({easing: 'easeInOutQuint', speed: 1500});
                                                        $('#forgot_from_1').scrollTo({easing: 'easeInOutQuint', speed: 1500});
                                                        $('#signup_from_2').scrollTo({easing: 'easeInOutQuint', speed: 1500});
                                                        $('#forgot_from_2').scrollTo({easing: 'easeInOutQuint', speed: 1500});
                                                        $('#forgot_from_3').scrollTo({easing: 'easeInOutQuint', speed: 1500});


                                                        // set focus on input
                                                        var firstInput = $('section').find('input[type=text], input[type=email]').filter(':visible:first');

                                                        if (firstInput != null) {
                                                            firstInput.focus();
                                                        }

                                                        $('section').waypoint(function(direction) {
                                                            var target = $(this).find('input[type=text], input[type=email]').filter(':visible:first');
                                                            target.focus();
                                                        }, {
                                                            offset: 300
                                                        }).waypoint(function(direction) {
                                                            var target = $(this).find('input[type=text], input[type=email]').filter(':visible:first');
                                                            target.focus();
                                                        }, {
                                                            offset: -400
                                                        });


                                                        // animation handler
                                                        $('[data-animation-delay]').each(function() {
                                                            var animationDelay = $(this).data("animation-delay");
                                                            $(this).css({
                                                                "-webkit-animation-delay": animationDelay,
                                                                "-moz-animation-delay": animationDelay,
                                                                "-o-animation-delay": animationDelay,
                                                                "-ms-animation-delay": animationDelay,
                                                                "animation-delay": animationDelay
                                                            });
                                                        });

                                                        $('[data-animation]').waypoint(function(direction) {
                                                            if (direction == "down") {
                                                                $(this).addClass("animated " + $(this).data("animation"));
                                                            }
                                                        }, {
                                                            offset: '90%'
                                                        }).waypoint(function(direction) {
                                                            if (direction == "up") {
                                                                $(this).removeClass("animated " + $(this).data("animation"));
                                                            }
                                                        }, {
                                                            offset: '100%'
                                                        });

                                                    })(jQuery);
        </script> 
 
    </body>
</html>


