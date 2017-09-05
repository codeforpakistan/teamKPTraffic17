
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
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
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Update Traffic Education Info</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                   <?php if(isset($error)){ echo $error; }?>
                    <div class="form-group">
                      <label for="image" class="col-sm-3 control-label">Upload Image</label>
                      
                      <div class="col-sm-6">
                        <img class="img-responsive" width="80" height="50" src="<?php echo base_url()."uploads/traffic-education/".$record['image'];?>" />
                        
                         <input type="hidden" name="traffic_education_id" value="<?=$id?>" />
                         <input type="file" name="image" value="<?php $record['image'];?>" id="imgUpload">
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="imgTitle" class="col-sm-3 control-label">Image Title</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="image_title" value="<?php echo $record['image_title']; ?>" id="imgTitle" placeholder="Image Title">
                      </div>
                    </div>
                    <!-- textarea -->
                    <div class="form-group">
                      <label for="imgDesEng" class="col-sm-3 control-label">Image Description English</label>
                      
                      <div class="col-sm-6">
                      <textarea class="form-control" rows="3" name="image_description_eng" placeholder="Enter Image Description in English"><?php echo $record['image_description_eng'];?></textarea>
                        </div>
                    </div>
                      
                    <div class="form-group">
                      <label for="imgDesUrdu" class="col-sm-3 control-label">Image Description Urdu</label>

                      <div class="col-sm-6">
                          <textarea class="form-control" rows="3" name="image_description_urdu" id="imgDesUrdu" placeholder="Image Description in Urdu"><?php echo $record['image_description_urdu'];?></textarea>
                      </div>
                    </div>
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'admin/view_all_signs'; ?>" class="btn btn-default">Cancel</a>
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
