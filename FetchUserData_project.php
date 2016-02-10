<?php 

define('DB_USERNAME', 'a4914011_Homeliv');
define('DB_PASSWORD', 'anandbarnwal');
define('DB_HOST', 'mysql9.000webhost.com');
define('DB_NAME', 'a4914011_TestDB');

// Create connection
$conn = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);

if ($conn->connect_error) {
	die("Connection failed: " . $conn->connect_error);
} 

$email = $_POST['email'];
#$password = $_POST['password'];
mysqli_real_escape_string($conn, $email);
#mysqli_real_escape_string($conn, $password);
$sql = "SELECT DesignerProjects.project_id,DesignerProjects.email,DesignerProjects.project_title,DesignerProjects.project_status,DesignerProjects.username FROM DesignerProjects INNER JOIN SignUpHomeliv, WHERE DesignerProjects.email=SignUpHomeliv.email AND DesignerProjects.username = SignUpHomeliv.username;
$result = $conn->query($sql);

if ($result->num_rows == 1) {
	$user = array();
	// output data of each row
	while($row = $result->fetch_assoc()) {
		$user['project_id'] = $row['project_id'];
		$user['email'] = $row['email'];
		$user['project_title'] = $row['project_title'];
		$user['project_status'] = $row['project_status'];
		$user['username'] = $row['username'];
	}
	echo json_encode($user);
}
$conn->close();