<style>
    table.dataTable{ word-break: normal;}
</style>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?php echo $heading; ?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Traffic Education</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
    <!-- Main content dataTable -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">All Signs of Traffic Education</h3>
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
                  <th width="35">S.No</th>
                  <th>Image</th>
                  <th width="100">Image Title</th>
                  <th>Image Description English</th>
                  <th>Image Description Urdu</th>
                  <th width="65">Action</th>
                </tr>
                </thead>
                
                <tbody>
                <?php
                    $i=1;
                    if(isset($data)) foreach($data as $row){
                    //print_r($data);die;
                ?>
                <tr>
                 <td><?= $i;?></td>
                  <td>
                     <a href="<?= base_url()."uploads/traffic-education/".$row->image;?>" data-lightbox="<?php echo $row->image;?>" data-title="<?php echo $row->image;?>">
                          <img class="img-responsive" src="<?= base_url()."uploads/traffic-education/".$row->image;?>" style="width:80px; height:50px;" />
                      </a>
                  </td>
                  <td><?=$row->image_title;?></td>
                  <td><?= $row->image_description_eng;?></td>
                  <td><?= $row->image_description_urdu;?></td>
                  <td>
                      <a href="admin/edit_traffic_sign/<?=$row->traffic_education_id?>" class="btn btn-primary"><i class="fa fa-pencil-square-o"></i></a>
                      <a onclick="return confirm('Are you sure you want to delete this?');" href="admin/delete_sign/<?=$row->traffic_education_id?>" class="btn btn-danger"><i class="fa fa-trash-o"></i></a>
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
                  <th>Image</th>
                  <th>Image Title</th>
                  <th>Image Description English</th>
                  <th>Image Description Urdu</th>
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


    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
