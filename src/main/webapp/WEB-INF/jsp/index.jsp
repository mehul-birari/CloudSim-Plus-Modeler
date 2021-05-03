<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/script.js"></script>

    <title>CloudSim Modeler</title>
</head>
<body>
<div class="container">
    <div class="toppane"><h1>CloudSimPlus Modeler</h1></div>
    <div class="leftpane">
        <section>
            <div class="dc draggable"><img src="/images/datacenter.png" class="gallery" contenteditable="true"/> Datacenter</div>
            <div class="host draggable"><img src="/images/host.png" class="gallery" contenteditable="true"/> Host</div>
            <div class="vm draggable"><img src="/images/vm.png" class="gallery" contenteditable="true"/> VM</div>
            <div class="cloudlet draggable"><img src="/images/cloudlet.png" class="gallery" contenteditable="true"/> Cloudlet</div>
        </section>
    </div>
    <div class="canvas"></div>
    <div class="rightpane">
        <h1>Selected Content Table</h1>
        <span class="element"></span>
        <jsp:include page="tables.jsp" />
        <br>
        <button onclick='saveAttribute($("table:visible")[0].className, $(".element").text().trim(),$(".element").attr("data-module"))'>Save</button>
        <br>
        <button onclick="sendData()">Generate</button>
    </div>
</div>
</body>
<script>
    const canvas = $(".canvas")
    const selectedClass = "ui-selected";
    let dcIndex = 0
    let hostIndex = 0
    let vmIndex = 0
    let cloudletIndex = 0
    let hostList = []
    let dcList = []
    let vmList = []
    let cloudletList = []

    $(".draggable").draggable({
        start: function (e, ui) {
            $(this).addClass(selectedClass);
        },
        stop: function (e, ui) {
            $("." + selectedClass).css({
            });
        },
        helper: "clone",
        cancel: false,
    });
    canvas.droppable({
        drop: function(event, ui) {
            selectedModule(ui)
            let new_signature = ui.helper.is('.ui-resizable') ?
                ui.helper
                :
                $(ui.helper).clone().removeClass('tool ui-draggable ui-draggable-handle ui-draggable-dragging');
            $(this).append(new_signature);
            new_signature.draggable({
                helper: false
            }).resizable()

        }
    });


    function updateIndex(element){
        let child = element.children(".gallery")
        let module = ""
        let index = 0
        if(!child.hasClass('dc') && !child.hasClass('host') && !child.hasClass('vm') && !child.hasClass('cloudlet')) {
            if (element.hasClass('dc')) {
                index = ++dcIndex
                module = "dc"
            } else if (element.hasClass('host')) {
                index = ++hostIndex
                module = "host"
            } else if (element.hasClass('vm')) {
                index = ++vmIndex
                module = "vm"
            } else if (element.hasClass('cloudlet')) {
                index = ++cloudletIndex
                module = "cloudlet"
            }
            child.addClass(module + ' ' + index)
            child.attr('data-index',index)
        }
        return child
    }

    function selectedPanel(element,child){
        let module = ""
        $("table").css("display","none")
        if (element.hasClass('dc')) {
            module = "Datacenter"
            $(".datacenter_table").css("display","block")
        } else if (element.hasClass('host')) {
            module = "Host"
            $(".host_table").css("display","block")
        } else if (element.hasClass('vm')) {
            module = "Virtual Machine"
            $(".vm_table").css("display","block")
        } else if (element.hasClass('cloudlet')) {
            module = "Cloudlet"
            $(".cloudlet_table").css("display","block")
        }
        $("button").css("display","block")
        return module
    }

    function selectedModule(ui) {
        let child = updateIndex($(ui.helper))
        let module = selectedPanel($(ui.helper),child)
        let index = child.attr("data-index")
        $(".element").text(module+"_"+index)
        $(".element").attr('data-module',module)
    }

    function saveAttribute(table, element, module){
        debugger
        let attr_table = {"id":element.split("_")[1]}
        $("."+table+" tbody tr").each(function () {
            let self = $(this)
            let col_1_value =  self.find("td:eq(0)").attr("data-variable")
            let col_2_value = self.find(".value").val()
            attr_table[col_1_value] = col_2_value
        });
        addToList(module,attr_table)

    }
    function addToList(module, attr_table) {
        if(module == "Host"){
            hostList.push(attr_table)
        }else if(module == "Datacenter"){
            dcList.push(attr_table)
        }else if(module == "Virtual Machine"){
            vmList.push(attr_table)
        }else if(module == "Cloudlet"){
            cloudletList.push(attr_table)
        }

    }

    function sendData(){
        $.ajax({
            type: "POST",
            url: "/sendDataDC",
            data: JSON.stringify(dcList),
            contentType : "application/json"
        });

        $.ajax({
            type: "POST",
            url: "/sendDataHost",
            data: JSON.stringify(hostList),
            contentType : "application/json"
        });

        $.ajax({
            type: "POST",
            url: "/sendDataVM",
            data: JSON.stringify(vmList),
            contentType : "application/json"
        });

        $.ajax({
            type: "POST",
            url: "/sendDataCloudlet",
            data: JSON.stringify(cloudletList),
            contentType : "application/json"
        });
    }

</script>
</html>
