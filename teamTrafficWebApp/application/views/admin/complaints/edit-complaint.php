
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Complaints</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Main content dataTable -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Update Complaint</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                   
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Complaint Status</label>
                      <input type="hidden" name="complaint_id" value="<?=$id?>" />
                      
                      <div class="col-sm-6">
                          <select name="status" class="form-control">
                            <!--<option>Choose Complaint Status</option>-->
                            <?php 
                              if(isset($status)) foreach($status as $row){
                            ?>
                            <option value="<?= $row->complaints_status_id;?>" <?php if($record['complaints_status_id'] == $row->complaints_status_id){ echo 'selected="selected"'; }?>><?= $row->status;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                    
                    <!-- textarea -->
                    <div class="form-group">
                      <label for="description" class="col-sm-3 control-label">Complaint Description</label>
                      
                      <div class="col-sm-6">
                      <textarea class="form-control" rows="3" name="description" placeholder="Enter Complaint Description" disabled><?php echo $record['description'];?></textarea>
                        </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="image" class="col-sm-3 control-label">Upload Image/Video</label>
                      
                      <div class="col-sm-6">
                        <?php if($record['image']) {?>
                          <img class="img-responsive" src="<?= base_url()."uploads/images/".$record['image'];?>" style="width:80px; height:50px;" />
                          <?php } else if($record['video']) { ?>
                          <video width="160" height="120" controls autoplay>
                             <source src="<?= base_url()."uploads/videos/".$record['video']; ?>" type="video/mp4">
                             Sorry, your browser doesn't support the video element.
                          </video>
                       <?php }else { echo 'Sorry! No image/video to display'; }?>
                        
                         <input type="file" name="image" value="" id="imgUpload" disabled>
                      </div>
                    </div>
                    
                   <!-- select -->
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Complaint Type</label>
                      <div class="col-sm-6">
                          <select name="type" class="form-control" disabled>
                            <option>Choose Complaint Type</option>
                            <?php 
                              if(isset($types)) foreach($types as $row){
                            ?>
                            <option value="<?= $row->complaint_type_id;?>" <?php if($record['complaint_type_id'] == $row->complaint_type_id){ echo 'selected="selected"'; }?>><?= $row->complaint_type;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="dated" class="col-sm-3 control-label">Date</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="dated" value="<?php echo date('d-m-Y', strtotime($record['dated'])); ?>" id="lat" placeholder="Enter Date" disabled>
                      </div>
                    </div>
                <!--                    
                    <div class="form-group">
                      <label for="lat" class="col-sm-3 control-label">Latitude</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="lat" value="<?php //echo $record['latitude']; ?>" id="lat" placeholder="Enter Latitude" disabled>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="lng" class="col-sm-3 control-label">Longitude</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="lng" value="<?php //echo $record['longitude']; ?>" id="lng" placeholder="Enter Longitude" disabled>
                      </div>
                    </div>
                -->
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'admin/get_complaints'; ?>" class="btn btn-default">Cancel</a>
                        <button type="submit" class="btn btn-info">Update</button>
                    </div>
                  </div>
                  <!-- /.box-footer -->
                </form>
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
