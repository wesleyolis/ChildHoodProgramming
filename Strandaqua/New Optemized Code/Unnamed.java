//please read the terms and conditions of the site before proceeding
window.status = "Downloading.... Filter Functions";
//Varibles
res = "";
first = true;
showing = false;
tevents = 0;
LoadWin = null;
function size (s, t)
{
    for (i = s.toString ().length ; i < t ; i++)
    {
	s = s + "&nbsp;";
    }


    return s;
}
function span (s, t)
{
    s = "<span style='Width:" + (t * 10) + ";'>" + s + "</span>";
    return s;
}

//writes the filters
function fil ()
{
    parent.h.document.writeln ("<html><head><title>Filters<\/title><\/head><body background='../images/back.jpg' topmargin='0' leftmargin='0'>");
    parent.h.document.write ("<form name='F'><table border='0' width='640' cellspacing='0' cellpadding='0'><tr><td align='center'><table border='0' width='590' cellspacing='4' cellpadding='0'><tr><td colspan='2'><p align='center'><b><font size='4'>Results Filters<\/font><\/b><\/p><\/td><td colspan='3' align='center'><b>Clubs&nbsp; <\/b><font size='1'><select size='1' name='Club' onchange='parent.page()' style='font-weight: 700;position:relative; width:300'><option selected>All Clubs<\/option>");
    for (i = 0 ; i < (parent.clubs.length - 2) ; i = i + 2)
    {
	parent.h.document.write ("<option value='" + parent.clubs [i + 1] + "'>" + parent.clubs [i] + "&nbsp;&nbsp;&nbsp;" + parent.clubs [i + 1] + "</option>");
    }


    parent.h.document.write ("</select></font><b>&nbsp;&nbsp;<button name='Print' style='font-size: 10pt; font-weight: bold; width: 50px; height:25px' onclick='parent.m.focus();parent.m.window.print(2)'>Print</button></b></td></tr><tr><td width='36'><b>&nbsp;Age</b></td><td width='95'><select size='1' name='Age' style='font-weight: 700; width:95; height:21' onchange='parent.page()'><option selected>All</option>");
    for (i = 6 ; i < 26 ; i++)
    {
	parent.h.document.write ("<option>" + i + "<\/option>");
    }


    parent.h.document.write ("</select></td><td width='132px'><b>Stroke&nbsp;&nbsp;&nbsp;</b><font size='1'><select size='1' onchange='parent.page()' name='stroke' style='font-weight: 700; position:relative'><option selected>All</option><option>Free</option><option>Back</option><option>Breast</option><option>Fly</option><option>Medley</option></select></font></td><td align='left' width='150px'><b>Distance </b><font size='1'><select size='1' onchange='parent.page()' name='dis' style='font-weight: 700; width:60; height:25'><option value='All'>All</option><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='400'>400</option><option value='500'>500</option><option  value='800'>800</option><option value='1500'>1500</option></select></font></td><td align='left'><b><input type='checkbox' onclick='parent.page()' name='rel' value='1' checked>Include Relays</b></td></tr><tr><td width='36'><b>Show</b></td><td width='95'><b><select size='1' onchange='parent.page()' name='show' style='font-weight: 700; width:94; height:22'><option selected>Both Sex</option><option>Female</option><option>Male</option></select></b></td><td colspan='2'><b>Include&nbsp; <select size='1' name='top' onchange='if(parent.h.document.F.Age.selectedIndex!=0 || parent.h.document.F.Club.selectedIndex!=0){parent.page()}'  style='font-weight: 700'><option selected>0</option>");
    for (i = 1 ; i < 11 ; i++)
    {
	parent.h.document.write ("<option>" + i + "<\/option>");
    }


    parent.h.document.write ("\n</select>&nbsp; top places in </b><font size='1'><select size='1' name='filter' onchange='if(parent.h.document.F.Age.selectedIndex!=0 || parent.h.document.F.Club.selectedIndex!=0){parent.page()}'  style='font-weight: 700; position:relative'><option>events</option><option selected>filter</option></select></font></td><td><b><input type='checkbox' onclick='parent.page()' name='fin' value='1' checked> Include Finals</b></td></tr><tr><td colspan='2'><b><button name='Jump' style='font-size: 10pt; font-weight: bold; width: 135px; height: 25px' onclick='parent.m.document.all.Events.style.pixelLeft=80;parent.m.window.scroll(0,0);'>Jump to Event &gt;&gt;</button></b></td><td colspan='2'><b>Include&nbsp; <select size='1' name='around' onchange='if(parent.h.document.F.Age.selectedIndex!=0 || parent.h.document.F.Club.selectedIndex!=0){parent.page()}'  style='font-weight: 700; width: 45; height: 22'><option selected>0</option><option>1</option><option>2</option><option>3</option><option>4</option></select>&nbsp; before and after filtered</b></td><td><b><input type='checkbox' onclick='parent.page()' name='pre' value='1' checked>Include Prelims</b></td></tr></table></tr></tr></table></form><\/body><\/html>");
    parent.h.document.close ();
}

function gen (s)
{
    if (s == "F")
    {
	s = "Female"
    }


    else
    {
	if (s == "M")
	{
	    s = "Male"
	}
	else
	{
	    s = "Mixed"
	}
    }


    return s;
}

function stk (s)
{
    if (s == 1)
    {
	return "Freestyle";
    }


    if (s == 2)
    {
	return "Backstroke";
    }


    if (s == 3)
    {
	return "Breaststroke";
    }


    if (s == 4)
    {
	return "Butterfly";
    }


    if (s == 5)
    {
	return "Medley";
    }
}

function type (t)
{
    if (t == "I")
    {
	return "Individual";
    }


    else
    {
	return "Relay"
    }
}

function chk (age)
{
    sel = (parent.h.document.F.Age.selectedIndex + 5);
    end = (age % 100);
    be = (age - end) / 100;
    if (age < 99 & sel <= age)
    {
	return true;
    }


    else
    {
	if (end == 99 & be < sel)
	{
	    return true;
	}
	else
	{
	    if ((((be == end) & ((be - 1) < sel)) || ((be - 1) < sel)) & end >= sel)
	    {
		return true;
	    }
	    else
	    {
		return false;
	    }
	}
    }
}

window.status = "Downloading.... Filter Functions 25%";

function age (s)
{
    switch (s)
    {
	case "6":
	    s = "6 & Under";
	    break;
	case "8":
	    s = "8 & Under";
	    break;
	case "10":
	    s = "10 & Under";
	    break;
	case "1599":
	    s = "15 & Over";
	    break;
	case "99":
	    s = "Open  ";
	    break;
	case "708":
	    s = "7 - 8";
	    break;
	case "910":
	    s = "9 - 10";
	    break;
	case "1112":
	    s = "11 - 12";
	    break;
	case "1314":
	    s = "13 - 14";
	    break;
	case "1516":
	    s = "15 - 16";
	    break;
	case "1718":
	    s = "17 - 18";
	    break;
	case "808":
	    s = "8 - 8";
	    break;
	case "909":
	    s = "9 - 9";
	    break;
	case "1010":
	    s = "10 - 10";
	    break;
	case "1111":
	    s = "11 - 11";
	    break;
	case "1212":
	    s = "12 - 12";
	    break;
	case "1313":
	    s = "13 - 13";
	    break;
	case "1414":
	    s = "14 - 14";
	    break;
	case "1515":
	    s = "15 - 15";
	    break;
	case "1616":
	    s = "16 - 16";
	    break;
	case "1717":
	    s = "17 - 17";
	    break;
	case "1818":
	    s = "18 - 18";
	    break;
	default:
	    s = age2 (s);
	    break;
    }


    return s;
}

function age2 (s)
{
    if (s < 99)
    {
	s += " & Under";
    }


    else
    {
	if ((s % 100) == 99)
	{
	    s = (s.replace ("99", "")) + " & Over";
	}
	else
	{
	    s = Math.round (s / 100) + " - " + (s % 100);
	}
    }


    return s;
}

function page ()
{
    /*if(!first){
    if(parent.m.document.all.Events.style.pixelLeft!=-510)
    {showing=true}else{showing=false}}else{tevents=((parent.events.length-1)/8)}
    parent.m.document.clear();res="";
    parent.m.document.write("<html><head><Title>Results</Title><style><!--a{text-decoration:none;color:#000000;}a:hover{color:#FFFFFF;}--></style></head><bodybackground='../images/back.jpg'style='background-attachment:fixed'topmargin='0'leftmargin='0'><tableborder='0'width='600'cellspacing='0'cellpadding='0'><tr><tdalign='left'>");
    loadWin();resWin("ProccessingFilter","Alwaysmoreadvanced")
    filter();
    parent.m.document.write("</td></tr><t/able></body></html>");
    parent.m.document.close();
    parent.m.focus();
    if(showing==true)
    {
    parent.m.document.all.Events.style.pixelLeft=80}
    if(first==true)
    {first=false;}
    window.status="ProcessingFiltersFinshed";
    Winclose();res.length-3*/
    parent.m.document.clear ();
    for (i = 0 ; i < 200 ; i += 33)
    {
	parent.m.document.write (result2 (i, 33, 0));
    }


    parent.m.document.close ();
}

window.status = "Downloading.... Filter Functions 50%";
function filter ()
{
    pos = 0;
    Sex = "";
    if (parent.h.document.F.show.selectedIndex == 1)
    {
	Sex = "F"
    }


    else
    {
	Sex = "M"
    }


    b = "";
    b = b + "<div style='position: absolute; width: 470px; z-index: 1; left: -510; top: 0px; ' id='Events'><table cellpadding='0' cellspacing='0' width='461' height='200'><tr><td width='456'><table cellpadding='0' cellspacing='0' border='0' width='456' height='200'><tr><td><img alt='' width='0' height='1' src='images/MsSpacer.gif'></td><td><img alt='' width='0' height='1' src='images/MsSpacer.gif'></td><td height='1'><img alt='' width='0' height='1' src='images/MsSpacer.gif'></td></tr><tr><td><img alt='' src='images/MsoPnl_Cnr_tl_104BD.gif' width='15' height='19'></td><td bgcolor='#003399' nowrap><a herf='' onclick=\"document.all.Events.style.pixelLeft='-510'\"><b><font color='#FFFFFF'>" + size ("", 32) + "Click here on bar to close" + size ("", 32) + "</font></b></a></td><td height='19'><img alt='' src='images/MsoPnl_Cnr_tr_104BF.gif' width='15' height='19'></td></tr><tr><td bgcolor='#0066CC' valign='top' align='left' colspan='3' height='200'>"
	ses = 0
	are = false
	b += "<p style='margin-left:20px'>";
    count = 1;
    for (e = 0 ; e < (parent.events.length - 1) ; e = e + 8, count++)
    {
	window.status = "Processing Filter Event: " + count + " out of " + tevents;
	if (ses != parent.events [e + 1])
	{
	    b = b + "<p align='center'><b>Session: " + parent.events [e + 1] + "</b></p><p style='margin-left:15px'>";
	    ses = parent.events [e + 1];
	}
	if ((parent.h.document.F.show.selectedIndex == 0 || parent.events [e + 3] == Sex || parent.events [e + 3] == "X") & (parent.h.document.F.stroke.selectedIndex == 0 || parent.h.document.F.stroke.selectedIndex == parent.events [e + 4]) & (parent.h.document.F.dis.selectedIndex == 0 || parent.h.document.F.dis.value == parent.events [e + 5]) & (parent.h.document.F.rel.checked == true || (parent.h.document.F.rel.checked == false & parent.events [e + 6] == "I")) & (parent.h.document.F.Age.selectedIndex == 0 || chk (parent.events [e + 7]) == true) || (parent.h.document.F.filter.selectedIndex == 0 & parent.h.document.F.Age.selectedIndex == 0))
	{
	    setTimeout ("", 10);
	    //must loop until all types is proccessed start point
	    r = "";
	    if (parent.h.document.F.rel.checked == true & parent.events [e + 6] == "R")
	    {
		r += relay (pos, pos + (parent.events [e] * 21));
	    }

	    if (parent.h.document.F.fin.checked == true & parent.events [e + 6] == "I")
	    {
		f = result (e, pos, (pos + (parent.events [e] * 7) - 7));
		if (f.length > 230)
		{
		    f = "<div style='border-bottom-style: solid; border-bottom-width: 1px; padding-bottom: 1px'><p align='center'><b>Final</b></div></p>" + f;
		    r += f;
		}
	    }
	    //end of loop
	    if (parent.h.document.F.pre.checked == true & parent.events [e + 8] != 0 & parent.events [e + 6] == "I")
	    {
		p = result (e, (pos + (parent.events [e] * 7)), (pos + (parent.events [e] * 7) + (parent.events [e + 8] * 7) - 7));
		if (p.length > 230)
		{
		    p = "<div style='border-bottom-style: solid; border-bottom-width: 1px; padding-bottom: 1px'><p align='center'><b>Prelims</b></div></p>" + p;
		    r += p;
		}
	    }

	    if (r != "")
	    {
		res = res + "<p align='center'><b><span style='background-color: #FFFFFF;Width:600;'><A name='e" + e + "'>";
		res = res + size (parent.events [e + 2], 10) + size (gen (parent.events [e + 3]), 10) + size (age (parent.events [e + 7]), 18) + size (parent.events [e + 5], 7) + size (stk (parent.events [e + 4]), 18) + type (parent.events [e + 6]);
		res += "</A></span></b></p>";
		parent.m.document.write (res);
		res = "";
		parent.m.document.write (r);


		b += "<b><A href='#e" + e + "' onclick=\"document.all.Events.style.pixelLeft='-510'\">" + span (parent.events [e + 2], 6);
		b += span (gen (parent.events [e + 3]), 6);
		b += span (age (parent.events [e + 7]), 9) + span (parent.events [e + 5] + "m", 5) + span (stk (parent.events [e + 4]), 10) + type (parent.events [e + 6]) + "</A></b><br>";
	    }
	}
	if (parent.events [e + 6] == "I")
	{
	    pos += ((parent.events [e + 8] * 7) + (parent.events [e] * 7));
	}
	else
	{
	    pos += (parent.events [e] * 21);
	}

    }


    parent.m.document.write ("</p><br><br>");
    b = b + "<br></td></tr><tr><td width='15'><img alt='' src='images/MsoPnl_Cnr_bl_104C1.gif' width='15' height='19'></td><td nowrap bgcolor='#003399' width='426'><a herf='' onclick=\"document.all.Events.style.pixelLeft='-510'\"><b><font color='#FFFFFF'>" + size ("", 32) + "Click here on bar to close" + size ("", 32) + "</font></b></a></td><td height='19' width='15'><img alt='' src='images/MsoPnl_Cnr_br_104C3.gif' width='15' height='19'></td></tr></table></td><td height='200' width='5'><img alt='' width='5' height='100%' src='images/MsoPnl_sh_b_104BC.gif'></td></tr><tr><td colspan='2' height='5'><img alt='' width='460' height='5' src='images/MsoPnl_sh_r_104BB.gif'></td></tr></table></div>"
	parent.m.document.write (b);
}

function resbeaf (be, pos, larst)
{
    ok = false;
    a = (parent.h.document.F.around.selectedIndex * 7);
    first = pos - a;
    las = pos + a;
    if (first < be)
    {
	first = be;
    }


    if (las > larst)
    {
	las = larst;
    }


    for (c = first ; c <= las ; c += 7)
    {
	if (((parent.h.document.F.Age.selectedIndex + 5) == parent.results [c + 5] || parent.h.document.F.Club.value == parent.results [c + 3]))
	{
	    ok = true;
	}
    }


    return ok;
}
window.status = "Downloading.... Filter Functions 75%";

function result2 (pos, num, e)
{
    d = "<div align='Left'><table border='0' width='600px'><tr><td width='30'></td><td width='170'></td><td width='170'></td><td width='80'></td><td width='30'></td><td width='30' align='left'></td><td align='left'></td></tr>";
    end = pos + (num * 3);

    if ((parent.h.document.F.Age.selectedIndex > 0 || parent.h.document.F.Club.selectedIndex > 0) || (parent.h.document.F.show.selectedIndex > 0 & parent.events [e + 2] == "X"))
    {

	num = num - parent.h.document.F.top.selectedIndex;
	mch = new Array (num);
	if (parent.h.document.F.around.selectedIndex > 0 & (parent.h.document.F.Age.selectedIndex > 0 || parent.h.document.F.Club.selectedIndex > 0))
	{
	    if (parent.h.document.F.Club.selectedIndex != 0 & parent.h.document.F.Age.selectedIndex != 0)
	    {
		for (p = pos + (parent.h.document.F.top.selectedIndex * 3), c = 0 ; p < end ; p += 3, c++)
		{
		    if (((parent.h.document.F.Club.selectedIndex * 2) = (parent.ath [(parent.res [p + 1] * 1) + 2] + 1)) & (parent.h.document.F.Age.selectedIndex + 5 == (parent.ath [(parent.res [p + 1] * 1) + 4])))
		    {
			for (m = c - parent.h.document.F.around.selectedIndex ; m < c + parent.h.document.F.around.selectedIndex ; m++)
			{
			    mch [m] = true;
			}
		    }
		}
	    }
	    else
	    {
		if (parent.h.document.F.Club.selectedIndex != 0)
		{

		    for (p = pos + (parent.h.document.F.top.selectedIndex * 3), c = 0 ; p < end ; p += 3, c++)
		    {
			if ((parent.h.document.F.Club.selectedIndex * 2) = (parent.ath [(parent.res [p + 1] * 1) + 2] + 1))
			{
			    for (m = c - parent.h.document.F.around.selectedIndex ; m < c + parent.h.document.F.around.selectedIndex ; m++)
			    {
				mch [m] = true;
			    }
			}
		    }
		}
		else
		{


		    for (p = pos + (parent.h.document.F.top.selectedIndex * 3), c = 0 ; p < end ; p += 3, c++)
		    {
			if (parent.h.document.F.Age.selectedIndex + 5 == (parent.ath [(parent.res [p + 1] * 1) + 4]))
			{
			    for (m = c - parent.h.document.F.around.selectedIndex ; m < c + parent.h.document.F.around.selectedIndex ; m++)
			    {
				mch [m] = true;
			    }
			}
		    }
		}
	    }

	}

	if (parent.h.document.F.show.selectedIndex != 0 & parent.events [e + 2] == "X")
	{
	    for (p = pos + (parent.h.document.F.top.selectedIndex * 3), c = 0 ; p < end ; p += 3, c++)
	    {
		if (parent.h.document.F.Show.selectedIndex = 1 & (parent.ath [parent.res [p + 1] + 3]) != "F")
		{
		    mch [c] = false;
		}
	    }
	}
	//writes resluts of filtered
	alert ("done");
	for (p = pos ; p < pos + (parent.h.document.F.top.selectedIndex * 3) ; p += 3)
	{
	    d += "<tr><td><b>" + parent.res [p] + "</b></td><td><b>" + (parent.ath [parent.res [p + 1]]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 1]) + "</b></td><td><b>" + (parent.clubs [(parent.ath [(parent.res [p + 1] * 1) + 2] * 1) + 1]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 3]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 4]) + "</b></td><td><b>" + parent.res [p + 2] + "</b></td></tr>";
	}


	for (p = pos + (parent.h.document.F.top.selectedIndex * 3) ; p < end ; p += 3)
	{
	    if (mch [p] == true)
	    {
		d += "<tr><td><b>" + parent.res [p] + "</b></td><td><b>" + (parent.ath [parent.res [p + 1]]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 1]) + "</b></td><td><b>" + (parent.clubs [(parent.ath [(parent.res [p + 1] * 1) + 2] * 1) + 1]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 3]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 4]) + "</b></td><td><b>" + parent.res [p + 2] + "</b></td></tr>";
	    }
	}

	if (parent.h.document.F.show.selectedIndex > 0) //for filtering gender in mixed events
	{
	    ss = 'M';
	    if (parent.h.document.F.show.selectedIndex == 1)
	    {
		ss = 'F'
	    }
	    for (p = pos ; p < end ; p += 3)
	    {
		if ((parent.ath [(parent.res [p + 1] * 1) + 3]) == ss)
		{
		    d += "<tr><td><b>" + parent.res [p] + "</b></td><td><b>" + (parent.ath [parent.res [p + 1]]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 1]) + "</b></td><td><b>" + (parent.clubs [(parent.ath [(parent.res [p + 1] * 1) + 2] * 1) + 1]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 3]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 4]) + "</b></td><td><b>" + parent.res [p + 2] + "</b></td></tr>";
		}
	    }

	}

    }


    else
    {
	//no filtering required and writes non filted results
	for (p = pos ; p < end ; p += 3)
	{

	    d += "<tr><td><b>" + parent.res [p] + "</b></td><td><b>" + (parent.ath [parent.res [p + 1]]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 1]) + "</b></td><td><b>" + (parent.clubs [(parent.ath [(parent.res [p + 1] * 1) + 2] * 1) + 1]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 3]) + "</b></td><td><b>" + (parent.ath [(parent.res [p + 1] * 1) + 4]) + "</b></td><td><b>" + parent.res [p + 2] + "</b></td></tr>";
	}
    }


    d += "</table></div>";
    return d;
}





function result (e, pos, end)
{
    d = "";
    d += "<div align='Left'><table border='0' width='600px'><tr><td width='30'></td><td width='170'></td><td width='170'></td><td width='80'></td><td width='30'></td><td width='30' align='left'></td><td align='left'></td></tr>";
    for (i = pos ; i <= end ; i += 7)
    {
	if (((parent.h.document.F.top.selectedIndex >= parent.results [i] & parent.results [i] != "") || (parent.h.document.F.Age.selectedIndex == 0 & parent.h.document.F.Club.selectedIndex == 0) || resbeaf (pos, i, end)) & ((parent.results [i + 4] == "M" & parent.h.document.F.show.selectedIndex == 2) || (parent.results [i + 4] == "F" & parent.h.document.F.show.selectedIndex == 1) || (parent.h.document.F.show.selectedIndex == 0)))
	{
	    d += "<tr><td><b>" + parent.results [i] + "</b></td><td><b>" + parent.results [i + 1] + "</b></td><td><b>" + parent.results [i + 2] + "</b></td><td><b>" + parent.results [i + 3] + "</b></td><td><b>" + parent.results [i + 4] + "</b></td><td><b>" + parent.results [i + 5] + "</b></td><td><b>" + parent.results [i + 6] + "</b></td></tr>";
	}
    }


    d += "</table></div>";
    return d;
}

function relay (pos, end)
{
    d = "";
    //Relay
    for (i = pos ; i < end ; i = i + 21)
    {
	d += "<div align='Left'><table border='0' width='600' cellspacing='0' cellpadding='0'><tr bgColor='#99CCFF'><td><b>Position: " + parent.results [i] + "</b></td><td width='250'><b>" + parent.results [i + 1] + " - " + parent.results [i + 2] + "</b></td><td width='60'><b>" + parent.results [i + 3] + "</b></td><td width='60'><b>" + parent.results [i + 4] + "</b></td></tr><tr><td><b>" + parent.results [i + 5] + "</b></td><td><b>" + parent.results [i + 6] + "</b></td><td><b>" + gen (parent.results [i + 7]) + "</b></td><td><b>" + parent.results [i + 8] + "</b></td></tr><tr><td><b>" + parent.results [i + 9] + "</b></td><td><b>" + parent.results [i + 10] + "</b></td><td><b>" + gen (parent.results [i + 11]) + "</b></td><td><b>" + parent.results [i + 12] + "</b></td></tr><tr><td><b>" + parent.results [i + 13] + "</b></td><td><b>" + parent.results [i + 14] + "</b></td><td><b>" + gen (parent.results [i + 15]) + "</b></td><td><b>" + parent.results [i + 16] + "</b></td></tr><tr><td><b>" + parent.results [i + 17] + "</b></td><td><b>" + parent.results [i + 18] + "</b></td><td><b>" + gen (parent.results [i + 19]) + "</b></td><td><b>" + parent.results [i + 20] + "</b></td></tr></table></div><br>";
    }


    return d;
}

function loadWin ()
{
    LoadWin = window.open ("", "", "width=250,height=150,Left=" + ((screen.Width / 2) - 125) + ",Top=" + ((screen.Height / 2) - 75));
}
function resWin (d, s)
{
    LoadWin.document.write ("<html><head><Title>Strand Aquatic's informer</Title></head><body bgColor='#0066CC' topmargin='0' leftmargin='0'>");
    LoadWin.document.write ("<p align='center'><font size='5'>" + d + "<\/font><\/p><p align='center'><img border='0' src='images/load.gif' width='180' height='25'></p><p align='center'><font size='5'>" + s + "<\/font><\/p><body><html>");
    LoadWin.document.close ();
}
function Winclose ()
{
    LoadWin.close ();
    LoadWin = null;
}
window.status = "Downloaded Filter Functions";
