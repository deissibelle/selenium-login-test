@echo off
echo =========================================
echo Tests BDD pour l'application Todo
echo URL: https://tasks-app-likz.onrender.com
echo =========================================

REM Vérifier Maven
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo Erreur: Maven n'est pas installé
    echo Téléchargez Maven depuis: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Nettoyer
echo Nettoyage des anciens résultats...
if exist target rmdir /s /q target

REM Exécuter les tests
echo Exécution des tests BDD...
echo.

echo 1. Tests d'API...
call mvn test -Dtest=TodoApiTestRunner

echo.
echo 2. Tests Frontend...
call mvn test -Dtest=TodoFrontendTestRunner

echo.
echo 3. Tous les tests...
call mvn test -Dtest=TodoTestRunner

echo.
echo =========================================
echo Résultats des tests:
echo =========================================
echo - Rapports HTML: target\cucumber-reports.html
echo - Rapports frontend: target\cucumber-frontend-reports.html
echo - Rapports API: target\cucumber-api-reports.html
echo =========================================

pause