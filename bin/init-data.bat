@echo off
rem /**
rem  * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
rem  *
rem  * Author: ThinkGem@163.com
rem  */
echo.
echo [��Ϣ] ��ʼ�����ݿ⣬�����������
echo.
pause
echo.
echo [��Ϣ] �˲�������������������ݱ����ָ���ʼ״̬��
echo.
echo [��Ϣ] ȷ�ϼ����𣿷�����رմ��ڡ���5��
echo.
pause
echo.
echo [��Ϣ] �����ȷ�ϼ����𣿷�����رմ��ڡ���4��
echo.
pause
echo.
echo [��Ϣ] �����ȷ�ϼ����𣿷�����رմ��ڡ���3��
echo.
pause
echo.
echo [��Ϣ] �����ȷ�ϼ����𣿷�����رմ��ڡ���2��
echo.
pause
echo.
echo [��Ϣ] �����ȷ�ϼ����𣿷�����رմ��ڡ���1��
echo.
pause
echo.

%~d0
cd %~dp0

cd ..
call mvn clean test -Dmaven.test.skip=false -Dtest=com.jeesite.test.InitCoreData -Djeesite.initdata=true -U

pause