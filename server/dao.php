<?php
class DAO {

    public function __construct($bdd, $resource) {
        $this->conn = $bdd;
        $this->resource= $resource;
    }

    public function get($id=null){
        $query = "SELECT * FROM ".$this->resource. ($id ? " where idlivraison=".$id : "");
        $response = array();
        $result = mysqli_query($this->conn, $query);
        while($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
		{
            $row = array_map(function($value){
                if(is_string($value))return utf8_encode($value);
                else return $value;
            },$row);
            $response[] = $row;
        }
        $response = ($id ? $response[0] : $response); 
        header('Content-Type: application/json');
		echo json_encode($response, JSON_PRETTY_PRINT);
    }


    public function add(){
        $query="INSERT INTO ".$this->resource."(".implode(",", array_keys($_POST)).") ";
        $query.= " VALUES(";
        foreach($_POST as $value){
            $query.= "'".$value."', ";
        }
        $query= substr($query, 0, -2).")";
        if(mysqli_query($this->conn, $query))
		{
			$response=array(
				'status' => 1,
				'status_message' =>'Ressource ajouté avec succés.'
			);
		}
		else
		{
			$response=array(
				'status' => 0,
				'status_message' =>'ERREUR!.'. mysqli_error($this->conn)
			);
		}
		header('Content-Type: application/json');
		echo json_encode($response);
    }

    public function update($id){
        $_PUT = array();
		parse_str(file_get_contents('php://input'), $_PUT);
        $query="UPDATE ".$this->resource." SET ";
        foreach($_PUT as $key=>$value){
            $query.= $key."='".$value."', ";
        }
        $query= substr($query, 0, -2)." WHERE id=".$id;
        if(mysqli_query($this->conn, $query))
		{
			$response=array(
				'status' => 1,
				'status_message' =>'Ressource mis a jour avec succes.'
			);
		}
		else
		{
			$response=array(
				'status' => 0,
				'status_message' =>'Echec de la mise a jour de la ressource. '. mysqli_error($this->conn)
			);
			
		}
		
		header('Content-Type: application/json');
		echo json_encode($response);
    }

    public function delete($id){
        $query = "DELETE FROM ".$this->resource." WHERE id=".$id;
		if(mysqli_query($this->conn, $query))
		{
			$response=array(
				'status' => 1,
				'status_message' =>'Ressource supprimé avec succés.'
			);
		}
		else
		{
			$response=array(
				'status' => 0,
				'status_message' =>'La suppression de la ressource a échoué.'. mysqli_error($this->conn)
			);
		}
		header('Content-Type: application/json');
		echo json_encode($response);
    }
}
?>