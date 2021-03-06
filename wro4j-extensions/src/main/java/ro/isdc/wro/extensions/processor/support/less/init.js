/**
 * Creates location object used by less.js 
*/
var location = location || {
    protocol: "",
    hostname: "",
    port: ""
};
/**
 * The problem is that less.js runs in develoment mode and sets a timer (via
 * window.setInterval()). The env.rhino.js implementation of window.setInterval
 * spawns a backround thread (java.lang.Thread(Runnable)) to fire the timer
 * events, and the "window" object is never closed.
 */
window.less = {}; 
window.less.env = 'production';