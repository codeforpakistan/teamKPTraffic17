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
        // checks Image for UPLOAD
        if(!empty($_FILES['image']['name']))
        {
            $config['upload_path'] = 'uploads/images/';
            $config['allowed_types'] = 'jpg|jpeg|png|gif';
            $config['max_size'] = 400;
            $config['file_name'] = $_FILES['image']['name'];

            //Load upload library and initialize configuration
            $this->load->library('upload',$config);
            $this->upload->initialize($config);

            if($this->upload->do_upload('image'))
            {
                $uploadData = $this->upload->data();
                $image = $uploadData['file_name'];
            }else{
                // $image = $this->upload->display_errors();
                $this->response(array('message' => $this->upload->display_errors(), 'status' => 'false'));
            }
        }else{
            $image = '';
            //$this->response(array('message' => 'Choose an image to upload', 'status' => 'false'));
        }

        
        /***** Checks Video for UPLOAD *****/
        if(!empty($_FILES['video']['name']))
        {
            $video=array
                (
                    'upload_path'   => 'uploads/videos',
                    'allowed_types' => 'mp4|avi|wmv|mov|mpg|mpeg|3gp',
                    'max_size'      => '1024',
                    'file_name'     => $_FILES['video']['name']
                );
            $this->load->library('upload',$video);
            $this->upload->initialize($video);

            if($this->upload->do_upload('video'))
            {
                $uploadData = $this->upload->data();
                $uploaded_video = $uploadData['file_name'];
            }else{
                $this->response(array('message' => $this->upload->display_errors(), 'status' => 'false'));
            }
        }else{
             $uploaded_video = '';
           // $this->response(array('message' => 'Choose a Video to upload', 'status' => 'false'));
        }
        /***** End video updation *****/
    
        // prepare array of user data
        $data = array
            (
                'complaint_type_id'  => $this->input->post('complaint_type_id'),
                'signup_id'          => $this->input->post('signup_id'),
                'latitude'           => $this->input->post('latitude'),
                'longitude'          => $this->input->post('longitude'),
                'description'        => $this->input->post('description'),
                'image'              => $image,
                //'video'              => $uploaded_video
            );
        
        $data1 = array
            (
                'complaint_type_id'  => $this->input->post('complaint_type_id'),
                'signup_id'          => $this->input->post('signup_id'),
                'latitude'           => $this->input->post('latitude'),
                'longitude'          => $this->input->post('longitude'),
                'description'        => $this->input->post('description'),
                //'image'              => $image,
                'video'              => $uploaded_video
            );
        
        // pass data to model
        if((empty($image)) && (empty($uploaded_video)))
        {
            $response = 'Select Image or Video to upload!';
            //$this->response(array('message' => 'Select Image or Video to upload!', 'status' => 'false');
        }elseif(isset($video))
        {
            $insert = $this->Complaintsmodel->insert_complaint($data1);
        }else
        {
            $insert = $this->Complaintsmodel->insert_complaint($data);
        }

        if(isset($insert))
        {
            $this->response(array('message' => 'Complaint is done!', 'status' => 'true', 'data'=>$insert));
        }
        else{
            $this->response(array('message' => 'Sorry, Try again!', 'status' => 'false'));
        }
    }
}
?>