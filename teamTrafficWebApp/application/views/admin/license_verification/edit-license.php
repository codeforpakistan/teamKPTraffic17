
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">License Verification</li>
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
                  <h3 class="box-title">Update License Info</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="lic_number" class="col-sm-3 control-label">Driving License Number</label>
                      
                      <div class="col-sm-6">
                         <input type="hidden" name="lic_id" value="<?= $record['license_id']; ?>" />
                          <input type="text" class="form-control" name="lic_number" value="<?= $record['dl_number']; ?>" id="lic_number" maxlength="12" placeholder="Enter Driving License Number">
                          <?php echo '<span class="error">'. form_error('lic_number').'</span>'; ?>
                      </div>
                    </div> 
                     
                     <div class="form-group">
                      <label for="lic_expiry_date" class="col-sm-3 control-label">License Expiry Date</label>

                      <div class="col-sm-6">
                        <div class="input-group date">
                          <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                          </div>
                          <input type="text" class="form-control" name="lic_expiry_date" value="<?= $record['license_expiry_date']; ?>" id="datepicker" placeholder="Enter license Expiry Date">
                        </div>
                        <?php echo '<span class="error">'. form_error('lic_expiry_date').'</span>'; ?>
                      </div>
                    </div>             
                    
                    <input type="hidden" class="form-control" name="status" value="<?= $record['lic_status']; ?>" id="status">
                   <!-- <div class="form-group">
                      <label for="status" class="col-sm-3 control-label">License Status</label>
                      
                      <div class="col-sm-6">
                       <select name="status" id="status" required class="form-control">
                            <option value="3">Select Status</option>
                            <option value="0" <?php //if($record['lic_status'] == 0){ echo 'selected=selected'; }?>> License Expired</option>
                            <option value="1" <?php //if($record['lic_status'] == 1){ echo 'selected=selected'; }?>> License Verified</option>
                          </select>
                        <input type="text" class="form-control" name="status" value="<?php //if($record['lic_status'] == 1){ echo 'License Verified'; }else echo 'License Expired'; ?>" id="status" required placeholder="Enter status">
                      </div>
                    </div> -->           
                    
                    <div class="form-group">
                      <label for="cnic" class="col-sm-3 control-label">CNIC</label>
                      
                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="cnic" value="<?= $record['cnic']; ?>" maxlength="13" placeholder="Enter CNIC">
                        <?php echo '<span class="error">'. form_error('cnic').'</span>'; ?>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label for="name" class="col-sm-3 control-label">Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" value="<?= $record['name']; ?>" id="name" placeholder="Enter Name">
                        <?php echo '<span class="error">'. form_error('name').'</span>'; ?>
                      </div>
                    </div>  
                    
                    <div class="form-group">
                      <label for="father_name" class="col-sm-3 control-label">Father Name</label>

                      <div class="col-sm-6">
                        <input type="text" class="form-control" name="father_name" value="<?= $record['father_name']; ?>" id="father_name" placeholder="Enter Father Name">
                        <?php echo '<span class="error">'. form_error('father_name').'</span>'; ?>
                      </div>
                    </div>
                            
                    <div class="form-group">
                      <label for="lic_type" class="col-sm-3 control-label">License Type</label>

                      <div class="col-sm-6">
                           <select name="lic_type" id="lic_type" class="form-control">
                            <!--onmouseover="document.getElementById('selected_text').value=this.options[this.selectedIndex].text" onchange="document.getElementById('selected_text').value=this.options[this.selectedIndex].text"-->
                            <option value="0">Select license Type</option>
                            <?php
                                if(isset($lic_type)) foreach($lic_type as $row){ 
                            ?>
                            <option value="<?= $row->license_type;?>" <?php if($record['license_type'] == $row->license_type){ echo 'selected="selected"'; }?>><?= $row->license_type;?></option>
                            <?php }?>
                          </select>
                          <!--<input type="hidden" name="selected_text" id="selected_text" value="" />-->
                          <?php echo '<span class="error">'. form_error('lic_type').'</span>'; ?>
                      </div>
                    </div>
                     
                     <div class="form-group">
                      <label class="col-sm-3 control-label">District</label>
                      <div class="col-sm-6">
                          <select name="district" id="district" class="form-control">
                            <!--onmouseover="document.getElementById('select_district').value=this.options[this.selectedIndex].text" onchange="document.getElementById('select_district').value=this.options[this.selectedIndex].text"-->
                            <option value="0">Select District</option>
                            <?php
                                if(isset($districts)) foreach($districts as $row){ 
                            ?>
                            <option value="<?= $row->name;?>" <?php if($record['district'] == $row->name){ echo 'selected="selected"'; }?>><?= $row->name;?></option>
                            <?php }?>
                          </select>
                          <!--<input type="hidden" name="select_district" id="select_district" value="" />-->
                          <?php echo '<span class="error">'. form_error('district').'</span>'; ?>
                      </div>
                    </div>
                             
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'admin/get_license_list'; ?>" class="btn btn-default">Cancel</a>
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
