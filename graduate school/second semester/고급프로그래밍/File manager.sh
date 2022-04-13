#!/bin/bash

menu(){
    echo "------------------------------"
    echo "experiment 1"
    echo "Please enter your choice:"
    echo "Option (1) File Remove"
    echo "Option (2) File Move"
    echo "Option (3) Directory Remove"
    echo "Option (4) Directory Move"
    echo "Option (5) Print File"
    echo "Option (6) List"
    echo "Option (7) File Create"
    echo "(Q|q)uit"
    echo "------------------------------"
}

fileremove(){
echo "#usage: file remove <file>"
read -a arr
ARGLEN=${#arr[@]}
if [ $ARGLEN -eq 1 ]
then
    file=${arr[0]}
    if [ -f $file ]
    then
        rm $file
        echo "File remove $file" 
    else
    echo "File $file exists."
    fi
else
echo "Arguments are not equals to 1"
fi
}

filemove(){
echo "#usage: file move <file> to <file>"
read -a arr
ARGLEN=${#arr[@]}
if [ $ARGLEN -eq 2 ]
then
    src=${arr[0]}
    dst=${arr[1]}
    if [ -f $src ]
    then
        mv $src $dst
        echo "File move $src to $dst"
    else
    echo "File $src Does not exits"
    fi 
else
echo "Arguments are not equal to 2"
fi
}

directoryremove(){
echo "#usage: directory remove <dir>"
read -a arr
ARGLEN=${#arr[@]}
if [ $ARGLEN -eq 1 ]
then
    dir=${arr[0]}
    if [ -d $src ]
    then
        rm -r $dir
        echo "Directory remove $dir"
    else
    echo "Directory $src Does not exists"
    fi 
else
echo "Argument are not equal to 1"
fi
}

directorymove(){
echo "#usage: directory move <dir> to <dir>"
read -a arr
ARGLEN=${#arr[@]}
#echo "$ARGLEN"
if [ $ARGLEN -eq 2 ]
then
    src=${arr[0]}
    dst=${arr[1]}
    if [ -d $src ]
    then
        mv $src $dst
        echo "Directory move $src to $dst"
    else
    echo "Directory $src Does not exists"
    fi
else
echo "Arguments are not equals to 2"
fi
}

printfile(){
echo "#usage print <file>"
read -a arr
ARGLEN=${#arr[@]}
if [ $ARGLEN -eq 1 ]
then
    file=${arr[0]}
    if [ -f $file ]
    then
        cat $file
        echo "Print file $file"
    else
    echo "File $file Does not exists"
    fi
else
echo "Argument are not equals to 1"
fi
}

filelist(){
echo "#usage list <dir>"
read -a arr
ARGLEN=${#arr[@]}
if [ $ARGLEN -ne 2 ]
then
    dir=${arr[0]}
    if [ -d $dir ]
    then
        ls $dir
        echo "Print File list"
    else
    echo "Directory $dir Does not exits"
    fi
else
echo "Argument are not lower 1"
fi
}

filecreate(){
echo "#usage create <file>"
read -a arr
ARGLEN=${#arr[@]}
if [ $ARGLEN -eq 1 ]
then
    file=${arr[0]}
    if [ -f $file ] 
    then
        echo "File $file exists"
    else
        if [ -d $file ]
        then
            echo "Directory $file exists"
        else
        touch $file
        echo "Create file $file" 
        fi
    fi
else
echo "Argument are not eqals to 1"
fi
}

while :
do
    menu
    read -n1 -s
    case "$REPLY" in
    "1")  echo "File Remove" 
            fileremove
            ;;
    "2")  echo "File Move" 
            filemove
            ;;
    "3")  echo "Directory Remove"
            directoryremove
            ;;
    "4")  echo "Directory Move" 
            directorymove
            ;;
    "5")  echo "Print File"         
            printfile
            ;;
    "6")  echo "List"
            filelist
            ;;
    "7")  echo "File Create"  
            filecreate
            ;;
    "Q"|"q")  exit                      ;;
     * )  echo "invalid option"     ;;
    esac
    sleep 1
done
