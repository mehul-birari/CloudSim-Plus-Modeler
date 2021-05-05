<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/script.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.24/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.24/datatables.min.js"></script>

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
        <h3>Selected Content Table</h3>
        <span class="element"></span>
        <div class="tableTab">
            <jsp:include page="tables.jsp" />
        </div>
        <br>
        <button class="btn btn-primary" onclick="sendData()">Generate</button>
    </div>
</div>

<div class="outputDiv">
    <table class="outputTable">
        <thead>
        <tr>
            <th>Cloudlet ID</th>
            <th>Status</th>
            <th>DC ID</th>
            <th>Host ID</th>
            <th>Host PEs</th>
            <th>VM ID</th>
            <th>VM PEs</th>
            <th>CloudletLen</th>
            <th>CloudletPEs</th>
            <th>StartTime</th>
            <th>FinishTime</th>
            <th>ExecTime</th>
        </tr>
        </thead>
    </table>
</div>

<span class="output">
</span>

</body>
<script>
    $(document).ready( function () {
        $('.datatable').DataTable({
            "pageLength": 15,
            "paging":   false,
        });
        $('.divTable').hide();
        $('.outputDiv').hide();
        $("button").hide()
    } );

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
            if($(".divTable").is(":visible")){
                saveAttribute($(".divTable:visible")[0].className.split(" ")[1], $(".element").text().trim(),$(".element").attr("data-module"))

            }
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
        $(".divTable").hide()
        if (element.hasClass('dc')) {
            module = "Datacenter"
            $(".datacenter").show()
        } else if (element.hasClass('host')) {
            module = "Host"
            $(".host").show()
        } else if (element.hasClass('vm')) {
            module = "Virtual Machine"
            $(".vm").show()
        } else if (element.hasClass('cloudlet')) {
            module = "Cloudlet"
            $(".cloudlet").show()
        }
        $("button").show()
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
        let attr_table = {}
        attr_table = {"id":element.split("_")[1]}
        $("."+table+"_table"+" tbody tr").each(function () {
            let self = $(this)
            let col_1_value =  self.find("td:eq(0)").attr("data-variable")
            let col_2_value = self.find(".value").val()
            attr_table[col_1_value] = col_2_value
        });
        addToList(module,attr_table)

    }
    function addToList(module, attr_table) {
        if(module == "Host" && checkPresent(dcList, attr_table)) {
            hostList.push(attr_table)
        }else if(module == "Datacenter" && checkPresent(hostList, attr_table)){
            dcList.push(attr_table)
        }else if(module == "Virtual Machine" && checkPresent(vmList, attr_table)){
            vmList.push(attr_table)
        }else if(module == "Cloudlet" && checkPresent(cloudletList, attr_table)){
            cloudletList.push(attr_table)
        }
    }

    function checkPresent(componentList, attr_table) {
        for (let component in componentList) {
            if (component["id"] == attr_table["id"]) {
                component = attr_table
                return false
            }
        }
        return true
    }

    function mapHostforDatacenter(value) {
        var hostIdArray = value["host"].split(",");
        value["host"] = hostList.filter((host) => (hostIdArray.includes(host["id"])))
    }


    function sendData(){

        saveAttribute($(".divTable:visible")[0].className.split(" ")[1], $(".element").text().trim(),$(".element").attr("data-module"))
        dcList.forEach(mapHostforDatacenter)
        dcList.forEach(
            dc => {
                var object = {
                    datacenterRegistry: dc,
                    hostRegistryList: dc["host"],
                }

                $.ajax({
                    type: "POST",
                    url: "/sendDataDC",
                    data: JSON.stringify(object),
                    dataType: "JSON",
                    async:false,
                    contentType: "application/json",
                })
            }
        );

        var object = {
            vmRegistryList:vmList,
            cloudletRegistryList:cloudletList
        }
        $.ajax({
            type: "POST",
            url: "/sendDataVmCloudlet",
            data: JSON.stringify(object),
            contentType : "application/json",
            async:false,
        }).done(function (data) {
            $(".outputDiv").show()
            $('.outputTable').DataTable({
                "data": data,
                "columns": [
                    { "data": "cloudlet_id" },
                    { "data": "status" },
                    { "data": "dc_id" },
                    { "data": "host_id" },
                    { "data": "host_pes" },
                    { "data": "vm_id" },
                    { "data": "vm_pes" },
                    { "data": "cloudletLen" },
                    { "data": "cloudPes" },
                    { "data": "startTime" },
                    { "data": "finishTime" },
                    { "data": "execTime" }
                ]
            });
        });
    }

</script>
</html>
