<html>

  <style>

    body {
      background-image: url('images/FurmanPhoto.jpg');
    }
    h1 {
      color: white;
      text-align: center;
    }
    h2 {
      color: white;
      text-align: center;
    }
    h3 {
      color: white;
    }
    form {
      margin-top: 1px;
      margin-right: 2px;
      position: absolute;
      top: 0;
      right: 0;
    }
    button {
      height: 50px;
      width: 150px;
      color: #54585A
      background-color: #201547;
    }
    #bottom {
        position: absolute;
        bottom: 0;
        left: 0;
      }

  </style>

  <title> Contact Page </title>

  <body>

    <h1> Luke Kvamme, Marc D'Avanzo, Phillip Garcia </h1>
    <h2> Luke.kvamme@furman.edu, Marc.davanzo@furman.edu, Phillip.garcia@furman.edu </h2>

    <form action="/~lkvamme/FurmanArtGallery/">
      <button>
         Go back
      </button>
  </form>
   
  </body>
  <?php
    $ip=$_SERVER['REMOTE_ADDR'];
    echo "<div id='bottom'> <h3> And this is you!</h3>";
    echo "<h3> $ip </div></h3>";
  ?>

</html>