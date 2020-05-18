<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 12px 28px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 12px;
            margin: 4px 2px;
            transition-duration: 0.4s;
            cursor: pointer;
        }

        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #4CAF50;
            border-radius: 10px;
            margin-left: 20px;
        }

        .button1:hover {
            background-color: #4CAF50;
            color: white;
        }

        .button2 {
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
            border-radius: 10px;
            margin-left: 330px;
        }

        .button2:hover {
            background-color: #008CBA;
            color: white;
        }

    </style>
</head>
<body style="background-color: #f5f5f5">
<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center" style="width: border-box 500px">
                    <img src=${imgURL} alt="" height="250" width="250" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${name} <br> ${title}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6">
                    <form method="post">
                        <button type="submit" name="dislike" class="btn btn-outline-danger btn-block">
                            <span class="fa fa-times"></span> Dislike</button>
                    </form>
               </div>
                <div class="col-12 col-lg-6">
                    <form method="post">
                        <button type="submit" name="like" value="${id}" class="btn btn-outline-success btn-block"><span
                                    class="fa fa-heart"></span> Like</button>
                    </form>
                </div>
                <br><br><br>
                <div>
                    <form action="/liked">
                        <button class="button button1">Liked List</button>
                    </form>
                </div>
                <div>
                    <form method="post" action="/logOut">
                        <button class="button button2">Log Out</button>
                    </form>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>
