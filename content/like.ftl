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
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body style="background-color: #f5f5f5">
<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
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
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>
