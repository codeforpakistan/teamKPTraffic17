<!DOCTYPE html>
<html>
<head>
    <title>Excel Import Data</title>
    <meta charset="UTF-8">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
</head>
<body>
    <h1>Excel Import Data</h1>
    <?php
        echo form_open_multipart('excel-import/import-data');
        echo form_upload('file');
        echo '<br/>';
        echo form_submit(null, 'Upload');
        echo form_close();
    ?>
</body>
</html>