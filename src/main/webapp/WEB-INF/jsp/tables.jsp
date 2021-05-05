<div class="divTable datacenter">
    <table class="datatable datacenter_table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td data-variable="name">Name</td>
            <td><input type="text" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="host">Host</td>
            <td><input type="text" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="amount">Amount</td>
            <td><input type="number" min="1" class="value" value="1"/></td>
        </tr>
        <tr>
            <td data-variable="architecture">Architecture</td>
            <td><input type="text" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="os">OS</td>
            <td><input type="text" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="vmm">VMM</td>
            <td><input type="text" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="vmAllocationPolicy">VM Allocation Policy</td>
            <td>
                <select class="value">
                    <option value="Simple">Simple</option>
                    <option value="BestFit">BestFit</option>
                    <option value="Abstract">Abstract</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="vmMigration">VM Migration</td>
            <td>
                <select class="value">
                    <option value="true">Enable</option>
                    <option value="false">Disable</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="costPerSec">Cost Per Sec</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="costPerMem">Cost Per Mem</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="costPerStorage">Cost Per Storage</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="costPerBw">Cost Per Bw</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="divTable cloudlet">
    <table class="datatable cloudlet_table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td data-variable="amount">Amount</td>
            <td><input type="number" min="1" class="value" value="1"/></td>
        </tr>
        <tr>
            <td data-variable="length">Length</td>
            <td><input type="number" min="0" class="value" value="1000"/></td>
        </tr>
        <tr>
            <td data-variable="fileSize">File Size</td>
            <td><input type="number" min="0" class="value" value="1000"/></td>
        </tr>
        <tr>
            <td data-variable="outputSize">Output Size</td>
            <td><input type="number" min="0" class="value" value="1000"/></td>
        </tr>
        <tr>
            <td data-variable="pes">PES</td>
            <td><input type="number" min="0" class="value" value="1"/></td>
        </tr>
        <tr>
            <td data-variable="utilizationModelCpu">Utilization Model CPU</td>
            <td>
                <select class="value">
                    <option value="Full">Simple</option>
                    <option value="Null">BestFit</option>
                    <option value="Stochastic">Stochastic</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="utilizationModelRam">Utilization Model RAM</td>
            <td>
                <select class="value">
                    <option value="Full">Simple</option>
                    <option value="Null">BestFit</option>
                    <option value="Stochastic">Stochastic</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="utilizationModelBw">Utilization Model BW</td>
            <td>
                <select class="value">
                    <option value="Full">Simple</option>
                    <option value="Null">BestFit</option>
                    <option value="Stochastic">Stochastic</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="submissionDelay">Submission Delay</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        </tbody>
    </table>
</div>
<div class ="divTable host">
    <table class="datatable host_table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td data-variable="pes">PES</td>
            <td><input type="number" min="0" value="1" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="amount">Amount</td>
            <td><input type="number" min="0" value="1" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="mips">MIPS</td>
            <td><input type="number" min="0" value="5000"  step="0.01" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="maxPower">MAX Power</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="staticPowerPercent">Static Power Percent</td>
            <td><input type="number" min="0" step="0.01" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="ram">RAM</td>
            <td><input type="number" min="0"  value="20000"  class="value"/></td>
        </tr>
        <tr>
            <td data-variable="bw">BW</td>
            <td><input type="number" min="0"  value="10000"  class="value"/></td>
        </tr>
        <tr>
            <td data-variable="ramProvisioner">RAM Provisioner</td>
            <td>
                <select class="value">
                    <option value="Simple">Simple</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="bwProvisioner">BW Provisioner</td>
            <td>
                <select class="value">
                    <option value="Simple">Simple</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="peProvisioner">PE Provisioner</td>
            <td>
                <select class="value">
                    <option value="Simple">Simple</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="vmScheduler">VM Scheduler</td>
            <td>
                <select class="value">
                    <option value="TimeShared">TimeShared</option>
                    <option value="SpaceShared">SpaceShared</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="powerModel">Power Model</td>
            <td>
                <select class="value">
                    <option value="Linear">Linear</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="storage">Storage</td>
            <td><input type="number" min="0"  value="100000"  class="value"/></td>
        </tr>
        </tbody>
    </table>
</div>

<div class="divTable vm">
    <table class="datatable vm_table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td data-variable="pes">PES</td>
            <td><input type="number" min="0" value="1" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="amount">Amount</td>
            <td><input type="number" min="0" value="1" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="mips">MIPS</td>
            <td><input type="number" step ="0.01" min="0" value="1000" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="size">Size</td>
            <td><input type="number" min="0" value="1000" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="cloudletScheduler">Cloudlet Scheduler</td>
            <td>
                <select class="value">
                    <option value="TimeShared">TimeShared</option>
                    <option value="SpaceShared">SpaceShared</option>
                </select>
            </td>
        </tr>
        <tr>
            <td data-variable="ram">RAM</td>
            <td><input type="number" value="10000" min="0" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="bw">BW</td>
            <td><input type="number" value="10000"  min="0" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="prority">Priority</td>
            <td><input type="number" min="0" class="value"/></td>
        </tr>
        <tr>
            <td data-variable="vmm">VMM</td>
            <td><input type="text" class="value"/></td>
        </tr>
        </tbody>
    </table>
</div>