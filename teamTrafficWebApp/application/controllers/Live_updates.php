<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Live_updates extends CI_Controller {
    
    public function __construct()
    {
        parent::__construct();
    }
    
    public function get_updates()
    {
        $flag = $_GET['flag'];
        if(isset($flag) || !empty($flag))
        {
            $result = $this->Complaintsmodel->getUpdatedData($flag);
        }
        
        //print_r($result); die;
        
        if(!empty($result))
        {
            $response = array('message' => 'Route status updated!', 'status' => true, 'data' => $result);
            echo json_encode($response);
        }
        else{
            $response = array('message' => 'No Route was found', 'status' => false);
            echo json_encode($response);
        }
    }

}

?>