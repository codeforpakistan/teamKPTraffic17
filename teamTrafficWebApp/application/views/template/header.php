<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><?php echo $title; ?></title>
  <base href="<?php echo base_url();?>">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<?php echo base_url();?>assets/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- jvectormap -->
  <!--<link rel="stylesheet" href="assets/plugins/jvectormap/jquery-jvectormap-1.2.2.css">-->
  <!-- DataTables -->
  <!--<link rel="stylesheet" href="assets/plugins/datatables/dataTables.bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.bootstrap.min.css">-->
  <!-- Theme style -->
  <link rel="stylesheet" href="<?php echo base_url();?>assets/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<?php echo base_url();?>assets/dist/css/skins/_all-skins.min.css">
  
  <!-- DataTables -->
  <link rel="stylesheet" href="<?php echo base_url();?>assets/plugins/datatables/dataTables.bootstrap.css">
  
  <!-- Datepicker -->
  <link rel="stylesheet" href="<?php echo base_url();?>assets/plugins/datepicker/datepicker3.css">
  <!-- DateTimePicker -->
  <link rel="stylesheet" href="<?php echo base_url();?>assets/bootstrap/css/bootstrap-datetimepicker.min.css">
  
  <!-- Lightbox -->
  <link rel="stylesheet" href="<?php echo base_url();?>lightbox/dist/css/lightbox.min.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <!-- Script for Google Analytics -->
  <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-102310917-1', 'auto');
  ga('send', 'pageview');

  </script>
 
  <style>
      .box-body .error p{
          color: red;
          padding-top: 5px;
          margin-bottom: -1px;
      }
      
      .info-box a{
          color: #333;
      }
      
      .info-box a:hover{
          font-weight: bold;
      }
      
      #table.dataTable{
          word-break: break-word;
      }
      
      .login_footer{
          background-color:#3b5998; color:#ffffff; padding: 3px; text-align: center; font-size: 17px; font-weight: 600;
      }
      
      .disabled-link {
        pointer-events: none;
        cursor: default;
        opacity: 0.6;
      }
      
      .uploadexcel{
          float: right;
          display: block;
          margin-bottom: 10px;
      }
      
      .datefilter{
          float:right; display:block; margin:12px;
      }
  </style>
  
</head>