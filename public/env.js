//This class includes configs which are needed by the javascript code
(function (window) {
  window.__env = window.__env || {};

  // API url
  window.__env.apiUrl = 'https://hanifarithmetic.herokuapp.com';
  //window.__env.apiUrl = 'http://localhost:8080';

  // Base url
  window.__env.baseUrl = '/';

  // Whether or not to enable debug mode
  // Setting this to false will disable console output
  window.__env.enableDebug = true;
}(this));