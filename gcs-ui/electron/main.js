const electron = require('electron');
const { app, BrowserWindow, screen: electronScreen } = electron;
const path = require('path');
const isDev = require('electron-is-dev');

let mainWindow = null;
app.on('ready', createWindow);
app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') {
    app.quit()
  }
});
app.on('activate', function () {
    if (mainWindow === null) {
        createWindow()
      }
});
app.setAboutPanelOptions({
  applicationName: "Ground Control Station", 
  applicationVersion: "v1",
  version: "1.0.0",
  credits: "Credits",
  copyright: "Crystal Ball Technologies. All rights reserved."
});
function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1024,
    height: 1024,
    show: true,
    title: "Crystal Ball GCS",
    backgroundColor: 'white',
    webPreferences: {
      nodeIntegration: true,
      webSecurity: false
    }
  });
  mainWindow.loadURL(isDev ? 'http://localhost:3000' : `file://${path.join(__dirname, '../build/index.html')}`);
  mainWindow.on('closed', function () {
    mainWindow = null
  })
  mainWindow.on('page-title-updated', function (e) {
    e.preventDefault()
  });
}