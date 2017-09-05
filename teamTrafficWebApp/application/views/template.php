<?php

    $this->load->view('template/header');
    $this->load->view('template/header-navbar');
    $this->load->view('template/sidebar');
    $this->load->view($page_name);
    $this->load->view('template/footer'); 

?>