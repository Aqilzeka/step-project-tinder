<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Chat</title>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <link rel="stylesheet" href="css/font.css">

    <link rel="stylesheet" href="/content/css/font.css">
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


        .button2 {
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
            border-radius: 10px;
            margin-left: 315px;
        }

        .button2:hover {
            background-color: #008CBA;
            color: white;
        }

        .button1 {
            background-color: white;
            color: black;
            border: 2px solid #008CBA;
            border-radius: 10px;
            margin-left: 15px;
        }

        .button1:hover {
            background-color: #2fbf0f;
            color: white;
        }

    </style>
</head>
<body style="overflow-y: scroll">
<div class="container">

    <div class="row">
        <div class="chat-main col-6 offset-3">
            <div class="col-md-12 chat-header">

                <div class="row header-one text-white p-1">
                    <div class="col-md-6 name pl-2">
                        <i class="fa fa-comment"></i>
                        <h6 class="ml-1 mb-0">
                            ${userTo}
                        </h6>
                    </div>
                    <div class="col-md-6 options text-right pr-0">
                        <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                        <p class="arrow-up mb-0">
                            <i class="fa fa-arrow-up text-center pt-1"></i>
                        </p>
                        <i class="fa fa-times hover text-center pt-1"></i>
                    </div>
                </div>
                <div class="row header-two w-100">
                    <div class="col-md-6 options-left pl-1">
                        <i class="fa fa-video-camera mr-3"></i>
                        <i class="fa fa-user-plus"></i>
                    </div>
                    <div class="col-md-6 options-right text-right pr-2">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
            </div>
            <div class="chat-content">
                <div class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                    <ul>
                        <#list messages as message>

                            ${message}
                        </#list>
                    </ul>
                </div>
                <div class="col-md-12 p-2 msg-box border border-primary">
                    <div class="row">
                        <div class="col-md-2 options-left">
                            <i class="fa fa-smile-o"></i>
                        </div>
                        <div class="col-md-7 pl-0">
                            <form method="post" >
                                <label>
                                    <input type="text" style="visibility: visible" name="message" class="border-0"
                                           placeholder="Send message">
                                </label>
                                <button type="submit" name="send"><span>Send</span></button>
                            </form>
                        </div>
                        <div class="col-md-3 text-right options-right">
                            <i class="fa fa-picture-o mr-2"></i>
                        </div>
                    </div>
                </div >
                <div class="row">
                    <form action="/liked">
                        <button class="button button1">Liked List</button>
                    </form>
                    <form method="post" action="/logOut">
                        <button class="button button2">Log Out</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>