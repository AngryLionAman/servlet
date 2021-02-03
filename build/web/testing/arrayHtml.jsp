<html>
    <head>
        <script>
            var room = 1;
            function add_fields(val) {
                var elem = document.getElementById("ttt");
                elem.parentNode.removeChild(elem);
                for (i = 0; i < val.value; i++) {
                    room++;
                    var objTo = document.getElementById('room_fileds');
                    var divtest = document.createElement("div");
                    divtest.innerHTML = '<div class="label" id="ttt"></div><input type="text" name="value[]" placeholder="option '+ (room - 1) +'"></div>';
                    objTo.appendChild(divtest);
                }
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script>
            $('.label').remove()
        </script>
        <style>
            div { padding:10px;}
        </style>
    </head>
    <body>
        <select name="nuberOfOption" onchange="add_fields(this);">
            <option value="1">1</option>    
            <option value="2">2</option>    
            <option value="3">3</option>    
            <option value="4" selected="">4</option>    
            <option value="5">5</option>    
            <option value="6">6</option>    
            <option value="7">7</option>    
            <option value="8">8</option>    
            <option value="9">9</option>    
            <option value="10">10</option>    
        </select>
        <form name="v" action="aceptHtmlArgu.jsp">
            <div id="room_fileds">               
                <div class="label" id="ttt">			
                    <input type="text" name="value[]" placeholder="option 1"><br><br>
                    <input type="text" name="value[]" placeholder="option 2"><br><br>
                    <input type="text" name="value[]" placeholder="option 3"><br><br>
                    <input type="text" name="value[]" placeholder="option 4"><br><br>
                </div>
            </div>
            <input type="submit">
        </form>
    </body>
</html>