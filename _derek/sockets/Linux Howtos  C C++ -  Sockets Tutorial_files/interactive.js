var req = false;
function loadXMLDoc(url) {
  if(window.XMLHttpRequest) { try { req = new XMLHttpRequest(); } catch(e) { req = false; } }
  else if(window.ActiveXObject) { try { req = new ActiveXObject("Msxml2.XMLHTTP"); } catch(e) { try { req = new ActiveXObject("Microsoft.XMLHTTP"); } catch(e) { req = false; } } }
  if(req) { req.onreadystatechange = processReqChange; req.open("GET", url, true); req.send(""); }
}
function chk(ev)
{
  var key;
  if (ev) key=ev.which;
  else if (event) key=event.keyCode;
  if (key>9) {
    var id=document.getElementById("fulltextsearch");
    if (id) {
      var query=id.value+String.fromCharCode(key);
      hidesuggest();
      if (query.length>2)
        loadXMLDoc("/suggest.php?q="+query);
    }
  }
}
function processReqChange() 
{
  if (req.readyState == 4 && req.status == 200)
    eval(req.responseText);
}
function suggest(packarr,numhits)
{
  var sbox=document.getElementById("suggestions");
  var ibox=document.getElementById("fulltextsearch");
  if (sbox&&packarr[0])
  {
//    sbox.style.width=ibox.clientWidth+"px";
    sbox.style.display="block";
    var i=0;
    sbox.innerHTML="<ul>";
    while (packarr[i])
    {
      sbox.innerHTML+="<li onClick=\"document.getElementById('fulltextsearch').value='"+packarr[i]+"';hidesuggest();\">"+packarr[i]+" ("+numhits[i]+")</li>";
      i++;
    }
    sbox.innerHTML+="</ul>";
    ibox.focus();
  }
}
function hidesuggest()
{
document.getElementById("suggestions").style.display="none";
}
