
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Live Traffic Updates</li>
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
                  <h3 class="box-title">Update Route Status</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                  
                    <div class="form-group">
                     <input type="hidden" name="route_update_id" value="<?=$id?>" />
                      <label class="col-sm-3 control-label">Select Route</label>
                      <div class="col-sm-6">
                          <select name="route" disabled required class="form-control">
                            <!--<option value="0">Select Route</option>-->
                            <?php
                                if(isset($route)) foreach($route as $row){ 
                            ?>
                            <option value="<?= $row->route_id;?>" <?php if($route_record['route_id'] == $row->route_id){ echo 'selected="selected"'; }?>><?= $row->route_name;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                  
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Select Route Status</label>
                      <div class="col-sm-6">
                          <select name="status" class="form-control">
                            <!--<option value="0">Select Route Status</option>-->
                            <?php
                                if(isset($route_status)) foreach($route_status as $row){ 
                            ?>
                            <option value="<?= $row->route_status_id;?>" <?php if($route_record['route_status_id'] == $row->route_status_id){ echo 'selected="selected"'; }?>><?= $row->route_status;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="time" class="col-sm-3 control-label">Updation Time</label>

                        <div class="col-sm-6">
                            <div class="input-group date form_datetime">
                              <input type="text" class="form-control" readonly size="16" name="time" value="<?php echo $route_record['updated_time']; ?>" placeholder="Enter Time Here">
                              <div class="input-group-addon">
                                <!--<span class="add-on"><i class="fa fa-remove"></i></span>-->
                                <span class="add-on"><i class="fa fa-calendar"></i></span>
                              </div>
                            </div>
                        </div>
                    </div>                
                     
                  </div>
                  <!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-offset-7 col-sm-3">
                        <a href="<?php echo base_url().'Admin/routes_list'; ?>" class="btn btn-default">Cancel</a>
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
