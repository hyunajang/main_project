<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='resources/packages/core/main.css' rel='stylesheet' />
<link href='resources/packages/daygrid/main.css' rel='stylesheet' />
<link href='resources/packages/timegrid/main.css' rel='stylesheet' />
<link href='resources/packages/list/main.css' rel='stylesheet' />
<script src='resources/packages/core/main.js'></script>
<script src='resources/packages/interaction/main.js'></script>
<script src='resources/packages/daygrid/main.js'></script>
<script src='resources/packages/timegrid/main.js'></script>
<script src='resources/packages/list/main.js'></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var data =  {"pNo" : ${pNo.pNo}};
    var events = getDate(data);
    events.push(getDate(data));
    console.log(events);
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
      height: 'parent',
      header: {
        left: 'prev,next,today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
      },
      defaultView: 'dayGridMonth',
      navLinks: true, // can click day/week names to navigate views
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      events : events
    });
    calendar.render();
  });
  
function getDate(data) {
    var events = [];
    $.ajax({
  	  	data: data,
			type: "get",
			url: "calendal.do",
       	success: function(result) {
            for(i=0; i < result.length; i++){
                events.push({
                   id: result[i].rsvNo,
                   title: result[i].title,
                   start: result[i].start
                });
            }
            events = JSON.stringify(events);
        }
    });
        return events;
}
</script>
<style>
html, body {
	overflow: hidden; /* don't do scrollbars */
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar-container {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
}

.fc-header-toolbar {
	padding-top: 1em;
	padding-left: 1em;
	padding-right: 1em;
}
</style>
</head>
<body>
	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>

</body>
</html>