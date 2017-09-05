
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>KP Traffic</b> App
    </div>
    <strong>Copyright &copy; 2017 <a href="http://codeforpakistan.org/" target="_blank">CFP Fellowship Program</a>.</strong> All rights
    reserved.
  </footer>

<!-- ./wrapper -->

<!-- jQuery 1.12.4 -->
<script src="<?php echo base_url();?>assets/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<?php echo base_url();?>assets/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<?php echo base_url();?>assets/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<?php echo base_url();?>assets/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- Datepicker -->
<script src="<?php echo base_url();?>assets/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- DateTimePicker-->
<script src="<?php echo base_url();?>assets/js/bootstrap-datetimepicker.js" charset="utf-8"></script>
<script src="<?php echo base_url();?>assets/js/locales/bootstrap-datetimepicker.fr.js" charset="utf-8"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
    });
</script>

<!-- Lightbox Jquery -->
<script src="<?php echo base_url();?>lightbox/dist/js/lightbox.min.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="<?php echo base_url();?>assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<?php echo base_url();?>assets/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<?php echo base_url();?>assets/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<?php echo base_url();?>assets/dist/js/demo.js"></script>
<!--
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
-->

<!-- Script for Delete function to fadeOut -->
<script>
$(document).ready(function(){
	$('#msg').delay(3000).fadeOut(5000);
});
    
    //Date picker
    $('#datepicker').datepicker({
      autoclose: true
    });
    
</script>

<!-- page script -->
<script>
  $(function () {
    $(document).ready(function() {
    $('#example').DataTable();
} );
  });
</script>


  <!-- Populate districts dropdown(Dashboard) -->
  <script type="text/javascript">
        function populateDistricts()
        {
         var id = $('#division').val();

         $.ajax({
            type: "POST",
            url: "http://localhost/kp-traffic-police/Admin_emergency/get_districts",
            data: {division_id:id}, 
                 success: function(districts)
                 {
                    $('#districts').html(districts);
                 }
            });

        }
    </script>
    
    <script type="text/javascript">
        function validate()
        {
            /*if($('#division').val() != 0){ 
                populateDistricts();
            }
            else */if ($('#division').val() == 0) {
                alert("Choose division first!");
            }
        }
    </script>
    
    
    <script>
        $('#cnic').keydown(function(){

          //allow  backspace, tab, ctrl+A, escape, carriage return
          if (event.keyCode == 8 || event.keyCode == 9 
                            || event.keyCode == 27 || event.keyCode == 13 
                            || (event.keyCode == 65 && event.ctrlKey === true) )
                                return;
          if((event.keyCode < 48 || event.keyCode > 57))
           event.preventDefault();

          var length = $(this).val().length; 

          if(length == 5 || length == 13)
           $(this).val($(this).val()+'-');

         });
        </script>
</body>
</html>
