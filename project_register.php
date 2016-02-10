<?php
		
    define('DB_USERNAME', 'a4914011_Homeliv');
    define('DB_PASSWORD', 'anandbarnwal');
    define('DB_HOST', 'mysql9.000webhost.com');
    define('DB_NAME', 'a4914011_TestDB');
    $con = mysql_connect(DB_HOST, DB_USERNAME, DB_PASSWORD) or die(mysql_error());  
    mysql_select_db(DB_NAME) or die(mysql_error());   


    $fname = $_POST["fname"];	
    $lname = $_POST["lname"];	
    $email = $_POST["email"];		
    $username = $_POST["username"];	
    $password = $_POST["password"];	
    $mobile = $_POST["mobile"];
		$project_title = $_POST["project_title"];
		$project_id = $_POST["project_id"];	
		
    
    $query = "INSERT INTO DesignerProjects(`project_id`, `email` ,`project_title` ,`project_status` ,`username`)  VALUES ('$project_id', '$email','$project_title','project_status','$username',)";
    $result = mysql_query($query) or die(mysql_error());

    mysql_close($con);
?>	
