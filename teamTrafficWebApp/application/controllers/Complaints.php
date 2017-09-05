<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Complaints extends CI_Controller {

    /*** Complaints with image uploading ***/
    public function image()
    {
        header('Content-Type:', 'image/jpeg');
        // checks Image for UPLOAD
        $image = $_FILES['image']['name'];
        $tempImage = $_FILES['image']['tmp_name'];
        
        if(!empty($image))
        {
            // $img_size = $_FILES['image']['size'];
            $upload = move_uploaded_file($tempImage,'uploads/images/'.$image);
            
            // prepare array of user data
            $dataImage = array
                (
                    'complaint_type_id'  => $this->input->post('complaint_type_id'),
                    'signup_id'          => $this->input->post('signup_id'),
                    'complaints_status_id'=> 2,
                    'latitude'           => $this->input->post('latitude'),
                    'longitude'          => $this->input->post('longitude'),
                    'description'        => $this->input->post('description'),
                    'image'              => $image,
                    'dated'              => date('Y-m-d')
                );
              //print_r($dataImage);die;
              $insert = $this->Complaintsmodel->InsertDB($dataImage);
              if($insert)
               {
                     $Response = array('message' => 'Complaint is done!', 'status' => true, 'data'=>$insert); 
                    echo json_encode($Response);
               }
               else
               {
                     $Response = array('message' => 'Sorry, Try again!', 'status' => false);
                    echo json_encode($Response);
               }
        }
        else
        {
            //$image = '';
            $Response = array('message' => 'Choose an image to upload', 'status' => false);
            echo json_encode($Response);
        } 
    }
    
    
    /*** Complaints With Video Uploading  ***/
    public function video()
    {
        /***** Checks Video for UPLOAD *****/
        if(!empty($_FILES['video']['name']))
        {
            $video = array
                (
                    'upload_path'   => 'uploads/videos',
                    'allowed_types' => 'mp4|avi|wmv|mov|mpg|mpeg|3gp',
                    'max_size'      => '10000',
                    'file_name'     => $_FILES['video']['name']
                );
            $this->load->library('upload',$video);
            $this->upload->initialize($video);

            if($this->upload->do_upload('video'))
            {
                $uploadData = $this->upload->data();
                $uploaded_video = $uploadData['file_name'];
            }else{
                $Response = array('message' => $this->upload->display_errors(), 'status' => false);
                echo json_encode($Response);
            }
        }else{
             //$uploaded_video = '';
            $Response = array('message' => 'Choose a Video to upload', 'status' => false);
            echo json_encode($Response);
        }
        // prepare video array for insert
        $dataVideo = array
            (
                'complaint_type_id'  => $this->input->post('complaint_type_id'),
                'signup_id'          => $this->input->post('signup_id'),
                'complaints_status_id'=> 2,
                'latitude'           => $this->input->post('latitude'),
                'longitude'          => $this->input->post('longitude'),
                'description'        => $this->input->post('description'),
                'video'              => $uploaded_video,
                'dated'              => date('Y-m-d')
            );
        if(isset($uploaded_video))
        {
            $insert = $this->Complaintsmodel->InsertDB($dataVideo);
            if($insert)
            {
                $Response = array('message' => 'Complaint is done!', 'status' => true, 'data'=>$insert); 
                echo json_encode($Response);
            }
           else
           {
                $Response = array('message' => 'Sorry, Try again!', 'status' => false);
                echo json_encode($Response);
           }
        }
    }
    
    public function myComplaints()
    {
       header('Content-Type: text/html; charset=utf-8');
        
        if(isset($_GET['user_id']))
        {
            $id = $_GET['user_id'];
            $result = $this->Complaintsmodel->my_complaints('complaints',$id);

            if(!empty($result))
            {
                $Response = array('message' => 'Success!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
            else{
                $Response = array('message' => 'No record exists!', 'status' => false);
                echo json_encode($Response);
            }
        }else{
            echo 'No User Record To Get';
        }
    }
    
    public function completedComplaints()
    {
       header('Content-Type: text/html; charset=utf-8');
        
        if(isset($_GET['user_id']))
        {
            $id = $_GET['user_id'];
            $result = $this->Complaintsmodel->completed_complaints('complaints',$id);

            if(!empty($result))
            {
                $Response = array('message' => 'Success!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
            else{
                $Response = array('message' => 'No Complaints to show!', 'status' => false);
                echo json_encode($Response);
            }
        }else{
            echo 'No User Record To Get';
        }
    }
    
    public function pendingComplaints()
    {
       header('Content-Type: text/html; charset=utf-8');
        
        if(isset($_GET['user_id']))
        {
            $id = $_GET['user_id'];
            $result = $this->Complaintsmodel->pending_complaints('complaints',$id);

            if(!empty($result))
            {
                $Response = array('message' => 'Success!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
            else{
                $Response = array('message' => 'No pending complaints to show!', 'status' => false);
                echo json_encode($Response);
            }
        }else{
            echo 'No User Record To Get';
        }
    }
    
    public function inprogressComplaints()
    {
       header('Content-Type: text/html; charset=utf-8');
        
        if(isset($_GET['user_id']))
        {
            $id = $_GET['user_id'];
            $result = $this->Complaintsmodel->inprogress_complaints('complaints',$id);

            if(!empty($result))
            {
                $Response = array('message' => 'Success!', 'status' => true, 'data' => $result);
                echo json_encode($Response);
            }
            else{
                $Response = array('message' => 'No record for InProgress complaints!', 'status' => false);
                echo json_encode($Response);
            }
        }else{
            echo 'No User Record To Get';
        }
    }
}
?>
