<?php
// include google analyticsTracking
include("includes/analyticsTracking.php");

error_reporting(0);
//function to get address by given latitude and longitude
  function getAddress($lat, $lon){
    //Google map API (Freely Available)
       $url  = "http://maps.googleapis.com/maps/api/geocode/json?latlng=".
                $lat.",".$lon."&sensor=false";
    //Get content from google map api in json format
       $json = @file_get_contents($url);
    //decode data
       $data = json_decode($json);
       $status = $data->status;
       $address = '';
       if($status == "OK"){
          $address = $data->results[0]->formatted_address;
        }
       return $address;
      }
?>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?php echo $heading; ?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Complaints</li>
      </ol>
    </section>
    
    <!-- Main content dataTable -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">ALL Complaints List</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                 <!-- session message -->
                <?php if($this->session->flashdata('msg')):?>
                <div class="callout callout-success" id="msg">
                    <p align="center" style="position:relative; font-size:16px;">
                        <?=$this->session->flashdata('msg')?>
                    </p>
                </div>
                <?php endif;?>
              <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                  <th>S.No</th>
                  <th width="25%">Complaint Description</th>
                  <th>Image / Video</th>
                  <th>Name</th>
                  <th>Phone No</th>
                  <th>Date</th>
                  <th>Address</th>
                  <!--<th>Latitude</th>-->
                  <th>Complaint Type</th>
                  <th>Complaint Status</th>
                  <th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                <?php
                    $i=1;
                    if(isset($data)) foreach($data as $row){
                    //print_r($data);die;
                    $latitude = $row->latitude;
                    $longitude = $row->longitude;
                        
                    // Call Above function at top of page
                    $address = getAddress($latitude, $longitude);
                ?>
                <tr>
                  <td><?= $i;?></td>
                  <td><?= $row->description;?></td>
                  <td>
                     <?php if($row->image) {?>
                          <a href="<?= base_url()."uploads/images/".$row->image;?>" data-lightbox="<?php echo $row->image;?>" data-title="<?php echo $row->image;?>">
                              <img class="img-responsive" src="<?= base_url()."uploads/images/".$row->image;?>" style="width:80px; height:50px;" />
                          </a>
                          <?php } else if($row->video) { ?>
                          <video width="160" height="120" controls autoplay>
                             <source src="<?= base_url()."uploads/videos/".$row->video; ?>" type="video/mp4">
                             Sorry, your browser doesn't support the video element.
                          </video>
                      <?php }else { echo 'Sorry! No image/video to display'; }?>
                  </td>
                  <td><?= $row->name;?></td>
                  <td><?= $row->phone_no;?></td>
                  <td><?= date('d-M-Y', strtotime($row->dated));?></td>
                  <td><?php
                        if($address){
                            echo $address;
                        }else{
                             echo "Address Not found";
                  }?></td>
                  <td><?= $row->complaint_type;?></td>
                  <td>
                       <?php if($row->status == 'Completed'){ ?><span class="label label-success"><?php echo 'Completed'; ?></span> <?php }
                        else if($row->status == 'Pending'){ ?><span class="label label-danger"><?php echo 'Pending'; ?></span><?php }
                        else if($row->status == 'In Progress'){ ?><span class="label label-warning"><?php echo 'In Progress'; ?></span><?php }
                      ?>
                  </td>
                  <td>
                      <a href="admin/edit_complaint/<?=$row->complaint_id?>" class="btn btn-primary"><i class="fa fa-pencil-square-o"></i></a>
                      <?php if($row->status == 'Completed'){ ?>
                          <a onclick="return confirm('Are you sure you want to delete this?');" href="admin/delete_complaint/<?=$row->complaint_id?>" class="btn btn-danger"><i class="fa fa-trash-o"></i></a>
                      <?php }else{ ?>
                          <a onclick="return confirm('Are you sure you want to delete this?');" href="admin/delete_complaint/<?=$row->complaint_id?>" class="btn btn-danger disabled-link"><i class="fa fa-trash-o"></i></a>
                      <?php } ?>
                  </td>
                </tr>
                <?php
                    $i++;
                        }else echo "No record Found!";    
                ?>
                </tbody>
                <tfoot>
                <tr>
                  <th>S.No</th>
                  <th>Complaint Description</th>
                  <th>Image / Video</th>
                  <th>Name</th>
                  <th>Phone No</th>
                  <th>Date</th>
                  <th>Address</th>
                 <!-- <th>Latitude</th>-->
                  <th>Complaint Type</th>
                  <th>Complaint Status</th>
                  <th>Action</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->

  </div>
  <!-- /.content-wrapper -->