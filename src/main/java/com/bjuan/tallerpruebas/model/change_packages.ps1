$MyInvocation.MyCommand.Path | Split-Path -Parent | Set-Location
$folders = Get-ChildItem $modelpath -Directory



foreach ($folder in $folders) {
    Set-Location $folder

    $packagename = "package com.bjuan.tallerpruebas.model." + $folder.Name + ";"
    $javafiles = Get-ChildItem -Filter "*.java"
    foreach ($file in $javafiles) {
        $filecontent = Get-Content $file
        $filecontent[0] = $packagename
        $filecontent | Set-Content $file
    }
}