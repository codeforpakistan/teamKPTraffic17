<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . '/libraries/REST_Controller.php';

class Complaints extends REST_Controller {
    
    public function __construct()
    {
        parent::__construct();

        $this->load->model('Complaintsmodel');
    }
    
    public function index_post()
    {
        // For Image
        $config=array
            (
                'upload_path'=>'uploads/images',
                'allowed_types'=>'jpg|jpeg|png|gif',
                'max_size'=>'20KB'
            );

        $this->load->library('upload',$config);
        $this->upload->initialize($config);
        // End Image
        
        if(!$this->upload->do_upload('image_path'))
        {
            $image = $this->upload->data('file_name');
            $this->response(array('message' => 'Image uploaded successfully!', 'status' => 'true'));
        }
        else
        {
            $image = $this->upload->display_errors();
        }
        
        /***** For Video Upload *****/
        
        $video=array
            (
                'upload_path'   => 'uploads/videos',
                'allowed_types' => 'mp4|avi|wmv|mov|mpg|mpeg',
                'max_size'      => '2MB'
            );

        $this->load->library('upload',$video);
        $this->upload->initialize($video);
        /***** End video updation *****/
        
      /*  if($this->upload->do_upload('video_path'))
        {
            $uploaded_video = $this->upload->data('file_name');
            
            try 
            {
            // Throws exception incase file is not being moved
                if (!$this->upload->do_upload('video_path'))
                {
                    // make error flag true
                    $this->response(array('message' => 'Could not move the video!', 'status' => 'false'));
                }

                // File successfully uploaded
                $uploaded_video = $this->upload->data('file_name');
                $this->response(array('message' => 'Video uploaded successfully!', 'status' => 'true'));
            } 

            catch (Exception $e) 
            {
            // Exception occurred. Make error flag true
                $this->response(array('message' => 'Video not uploaded, Try again!', 'status' => 'false'));
            }
        }
        else 
        {
            // File parameter is missing
            $this->response(array('message' => $this->upload->display_errors(), 'status' => 'false'));
        }
    
        /*if($this->upload->do_upload('video_path'))
        {
            $uploaded_video = $this->upload->data('file_name');
        }
        else{
            //$uploaded_video = $this->upload->display_errors();
            $this->response(array('message' => 'Error Uploading Video', 'status' => 'false'));
        }*/
        
        $data = array
            (
                'complaint_type_id'  => $this->input->post('complaint_type_id'),
                'signup_id'          => $this->input->post('signup_id'),
                'latitude'           => $this->input->post('latitude'),
                'longitude'          => $this->input->post('longitude'),
                'description'        => $this->input->post('description'),
                'image_path'         => $image,
                'video_path'         => $this->input->post('video_path') //$uploaded_video
            );
        $insert = $this->Complaintsmodel->insert_complaint($data);
        
        if($insert)
        {
            $this->response(array('message' => 'Complaint is done!', 'status' => 'true', 'data'=>$insert));
        }
        else{
            $this->response(array('message' => 'Sorry, Try again!', 'status' => 'false'));
        }
    }
}
?>