
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Emergency Contacts</li>
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
                  <h3 class="box-title">Update District</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                  
                  <input type="hidden" name="emergency_district_id" value="<?=$id?>" />
                  
                    <!-- select division -->
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Select Division</label>
                      <div class="col-sm-6">
                          <select name="division" id="division" required class="form-control">
                            <!--<option value="0">Select Division</option>-->
                            <?php
                                if(isset($division)) foreach($division as $row){ 
                            ?>
                            <option value="<?= $row->emergency_division_id;?>" <?php if($record['emergency_division_id'] == $row->emergency_division_id){ echo 'selected="selected"'; }?>><?= $row->division_name;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                    
                   <div class="form-group">
                      <label for="name" class="col-sm-3 control-label">District Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" required id="name" value="<?php echo $record['district_name']; ?>" placeholder="Enter Name Here">
                      </div>
                    </div>
                     
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'Admin_emergency/show_districts'; ?>" class="btn btn-default">Cancel</a>
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
