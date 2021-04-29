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
        <br/><br/>
        <table>
            <tr>
                <th>ID</th>
                <th>Value</th>
            </tr>
            <tr>
                <td>RAM</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>BW</td>
                <td><input type="text"></td>
            </tr>
            <tr>
                <td>Storage</td>
                <td><input type="text"></td>
            </tr>
        </table>
        <br/>
        <button>Save</button>
        <br>
        <br>
        <button>Generate</button>
    </div>
</div>
</body>
<script>
    const canvas = $(".canvas");
    const selectedClass = "ui-selected";
    var dcIndex = 0
    var hostIndex = 0
    var vmIndex = 0
    var cloudletIndex = 0

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
            var new_signature = ui.helper.is('.ui-resizable') ?
                ui.helper
                :
                $(ui.helper).clone().removeClass('tool ui-draggable ui-draggable-handle ui-draggable-dragging');
            $(this).append(new_signature);
            new_signature.draggable({
                helper: false
            }).resizable()

        }
    });

    function selectedModule(ui) {
        let element = $(ui.helper)
        var module = ""
        var child = element.children(".gallery")
        console.log(child)

        let index
        if(!child.hasClass('dc') && !child.hasClass('host') && !child.hasClass('vm') && !child.hasClass('cloudlet')) {
            if (element.hasClass('dc')) {
                dcIndex++
                index = dcIndex
                module = "dc"
            } else if (element.hasClass('host')) {
                hostIndex++
                index = hostIndex
                module = "host"
            } else if (element.hasClass('vm')) {
                vmIndex++
                index = vmIndex
                module = "vm"
            } else if (element.hasClass('cloudlet')) {
                cloudletIndex++
                index = cloudletIndex
                module = "cloudlet"
            }
            child.addClass(module + ' ' + index)
            child.attr('data-index',index)
        }

        //selected panel
        index = child.attr("data-index")
        if (element.hasClass('dc')) {
            module = "Datacenter"
        } else if (element.hasClass('host')) {
            module = "Host"
        } else if (element.hasClass('vm')) {
            module = "Virtual Machine"
        } else if (element.hasClass('cloudlet')) {
            module = "Cloudlet"
        }
        module += "_"+index

        $(".element").text(module);
        console.log(child)
    }

</script>
</html>
