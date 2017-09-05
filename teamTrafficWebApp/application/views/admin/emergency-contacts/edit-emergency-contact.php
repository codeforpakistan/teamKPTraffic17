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
                  <h3 class="box-title">Update Emergency Contact</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                  <!-- select category -->
                    <div class="form-group">
                     <input type="hidden" name="emergency_detail_id" value="<?=$id?>" />
                      <label class="col-sm-3 control-label">Select Category</label>
                      <div class="col-sm-6">
                          <select name="category" required class="form-control">
                            <!--<option value="0">Select Category</option>-->
                            <?php
                                if(isset($category)) foreach($category as $row){ 
                            ?>
                            <option value="<?= $row->emergency_category_id;?>" <?php if($record['emergency_category_id'] == $row->emergency_category_id){ echo 'selected="selected"'; }?>><?= $row->category_name;?></option>
                            <?php }?>
                          </select>
                          
                          <?php echo '<span class="error">'. form_error('category').'</span>'; ?>
                      </div>
                    </div>
                    <!-- select division -->
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Select Division</label>
                      <div class="col-sm-6">
                          <select name="division" id="division" onchange="populateDistricts()" class="form-control">
                            <!--<option value="0">Select Division</option>-->
                            <?php
                                if(isset($division)) foreach($division as $row){ 
                            ?>
                            <option value="<?= $row->emergency_division_id;?>" <?php if($record['emergency_division_id'] == $row->emergency_division_id){ echo 'selected="selected"'; }?>><?= $row->division_name;?></option>
                            <?php }?>
                          </select>
                          
                          <?php echo '<span class="error">'. form_error('division').'</span>'; ?>
                      </div>
                    </div>
                    <!-- select district -->
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Select District</label>
                      <div class="col-sm-6">
                          <select name="district" id="districts" class="form-control" onclick="validate()">
                            <option value=""><?= $record['district_name'];?></option>
                          </select>
                          
                          <?php echo '<span class="error">'. form_error('district').'</span>'; ?>
                      </div>
                    </div>
                    
                   <div class="form-group">
                      <label for="name" class="col-sm-3 control-label">Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" id="name" value="<?php echo $record['name']; ?>" placeholder="Enter Name Here">
                        <?php echo '<span class="error">'. form_error('name').'</span>'; ?>
                      </div>
                    </div>                    
                    <div class="form-group">
                      <label for="conatctNo" class="col-sm-3 control-label">Contact Number</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="contact" id="contactNo" value="<?php echo $record['contact_no']; ?>" placeholder="Enter Contact Number Here">
                        <?php echo '<span class="error">'. form_error('contact').'</span>'; ?>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="latitude" class="col-sm-3 control-label">Latitude</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="lat" id="lat" value="<?php echo $record['latitude']; ?>" placeholder="Enter Latitude">
                        <?php echo '<span class="error">'. form_error('lat').'</span>'; ?>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="longitude" class="col-sm-3 control-label">Longitude</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="lng" id="lng" value="<?php echo $record['longitude']; ?>" placeholder="Enter Longitude">
                        <?php echo '<span class="error">'. form_error('lng').'</span>'; ?>
                      </div>
                    </div>   
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'Admin_emergency/emergency_list'; ?>" class="btn btn-default">Cancel</a>
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
