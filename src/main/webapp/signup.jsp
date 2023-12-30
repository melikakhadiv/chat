<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="jsp/assets/css/style.css">
</head>
<body>
<div class="main">

    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>
                    <form action="/User" method="post" enctype="multipart/form-data" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="username" id="username" placeholder="Username"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-email"></i></label>
                            <input type="password" name="password" id="password" placeholder="Password"/>
                        </div>
                        <div class="form-group">
                            <label for="firstname"><i class="zmdi zmdi-lock"></i></label>
                            <input type="text" name="firstname" id="firstname" placeholder="Firstname"/>
                        </div>
                        <div class="form-group">
                            <label for="lastname"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="text" name="lastname" id="lastname" placeholder="Lastname"/>
                        </div>
                        <div class="form-group">
                            <label for="nickname"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="text" name="nickname" id="nickname" placeholder="Nickname"/>
                        </div>
                        <div class="form-group">
                            <input type="file" name="file" id="file" style="display: none" />
                            <label for="file" class="label-agree-term" style="border: #737573 solid 2px"><span><span></span></span>Choose Your Profile Image</label>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="privateAcc" id="privateAcc" class="agree-term" />
                            <label for="privateAcc" class="label-agree-term"><span><span></span></span>Private Account</label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="images/signup-image.jpg" alt="sing up image"></figure>
                    <a href="/index.jsp" class="signup-image-link">I am already member</a>
                </div>
            </div>
        </div>
    </section>
</div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="jsp/assets/js/main.js"></script>
</body>
</html>