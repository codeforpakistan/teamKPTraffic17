
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        <?= $heading;?>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Live Updates</li>
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
                  <h3 class="box-title">Add Route Status Info</h3>
                </div>
                <!-- /.box-header -->
                
                <!-- form start -->
                <form class="form-horizontal" action="<?= $action;?>" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                  <!-- select category -->
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Select Route</label>
                      <div class="col-sm-6">
                          <select name="route" class="form-control">
                            <option value="0">Select Route</option>
                            <?php
                                if(isset($route)) foreach($route as $row){ 
                            ?>
                            <option value="<?= $row->route_id;?>"><?= $row->route_name;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                    
                    <div class="form-group">
                      <label class="col-sm-3 control-label">Select Route Status</label>
                      <div class="col-sm-6">
                          <select name="status" class="form-control">
                            <option value="0">Select Status</option>
                            <?php
                                if(isset($status)) foreach($status as $row){ 
                            ?>
                            <option value="<?= $row->route_status_id;?>"><?= $row->route_status;?></option>
                            <?php }?>
                          </select>
                      </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="time" class="col-sm-3 control-label">Updation Time</label>

                        <div class="col-sm-6">
                            <div class="input-group date form_datetime">
                              <input type="text" class="form-control" readonly required size="16" name="time" placeholder="Enter Time Here">
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
                        <button type="reset" class="btn btn-default">Reset</button>
                        <button type="submit" class="btn btn-info">Submit</button>
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
