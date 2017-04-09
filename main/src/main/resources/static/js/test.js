var Main = React.createClass({


    getInitialState: function () {
        return {array: [["Router", "Load"], ["00:00", 0]]}
    },

    re: function () {
        var a = this.props.address;
        $.get("/device?action=cpu&address=" + a.replace("/", "")).done(function (data1) {
            var arr1 = [['Router', 'Load']];
            for (var i = 0; i < data1.length; i++) {
                var date = new Date(data1[i].timestamp);
                data1[i].timestamp = date.getHours() + ":" + date.getMinutes();
                arr1[i + 1] = [data1[i].timestamp, data1[i].load]
            }
            test(arr1)
        });

    },


    render: function () {
        this.re();
        if (this.state.typeRender == 'nothing') {
            return <div></div>
        }
        return (<h onClick={this.re}>Load</h>);
    }

});


function test(data) {
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var d = google.visualization.arrayToDataTable(data);

        var options = {
            title: 'Load',
            hAxis: {title: 'Time', titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('placeholder'));
        chart.draw(d, options);
    }
}

var Header = React.createClass({


    render: function () {
        return (
            <div className="navbar navbar-default" id="header">
                <a className="navbar-brand">Monitoring</a>
                <ul style={{cursor: "pointer"}} className="nav navbar-nav" id="menu">
                    <MainLink/>
                    <SomeLink/>
                </ul>
            </div>
        );
    }
});

var MainListOfDevice = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        $("#container1").html("");
        return null;
    },

    handlerMain: function (el) {
        $("#container").removeClass("non-visible");
        $("#placeholder").removeClass("non-visible");
        ReactDOM.render(
            <Main address={el}/>,
            document.getElementById('container')
        )
        ;

    },

    getInitialState: function () {
        return {listDevices: []}
    },

    componentDidMount: function () {
        $.get('/device?action=all').done(function (data) {
            this.setState({listDevices: data});
        }.bind(this));
    },

    render: function () {
        var console = this;
        return <ul id="list-texts" className="list-group">
            {

                this.state.listDevices.map(function (el, index) {
                    return <li key={index} onClick={() => console.handlerMain(el.ipAddress)}>{el.ipAddress}
                    </li>;

                })
            }
        </ul>
    }


});


var MainLink = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        return null;
    },

    handlerMain: function () {
        ReactDOM.render(
            <MainListOfDevice/>,
            document.getElementById('container')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Monitoring</a></li>)
    }
});

var SomeLink = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        $("#container1").html("");
        $("#placeholder").html("");
        return null;
    },


    handlerMain: function () {
        $("#placeholder").html("");
        ReactDOM.render(
            <Device/>,
            document.getElementById('container')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Devices</a></li>)
    }
});

var Device = React.createClass({

    getInitialState: function () {
        var hash = {
            typeHash: null,
            hashPassword: null
        }
        var encryption = {
            typeEncrypt: null,
            encryptionPassword: null,
        }
        var snmp = {
            hash: hash,
            encryption: encryption,
            version: "none",
            username: null
        }
        var Device = {
            vendor: null,
            address: null,
            snmp: snmp
        }
        return {Device: Device}
    },

    render: function () {
        console.log(this.state.Device.snmp.version)
        if (this.state.Device.snmp.version == "v3") {
            return <div>
                <p>
                    value="version" v3<select onChange={this.addVersion} type="text">
                    <option>v3</option>
                    <option>v1</option>
                    <option>v2</option>
                </select>
                </p>
                <p>
                    value="Vendor"Cisco<input onChange={this.addVendor} type="text"/>
                </p>
                <p>
                    value="Address"192.168.0.1<input onChange={this.addAddress} type="text"/>
                </p>


                <p>
                    value="username"operator<input onChange={this.addUsername} type="text"/>
                </p>
                <p>
                    value="typeHash" MD5<input onChange={this.addTypeHash} type="text"/>
                </p>
                <p>
                    value="HashPassword" AuthPassw0rd<input onChange={this.addHashPassword} type="text"/>
                </p>
                <p>
                    value="typeEncrypt" DES<input onChange={this.addTypeEncrypt} type="text"/>
                </p>
                <p>
                    value="EncryptPassword" EncryptionPassw0rd<input onChange={this.addEncryptPassword} type="text"/>
                </p>
                <p>
                    <input type="button" onClick={this.sendVal}/>
                </p>
            </div>
        } else {
            return <p>
                value="version" v3<select onChange={this.addVersion} type="text">
                <option>v1</option>
                <option>v2</option>
                <option>v3</option>
            </select>
            </p>;
        }

    },

    sendVal: function () {
        var stringify = JSON.stringify(this.state.Device);
        stringify = '{ "device" : ' + stringify + '}}';
        $.post("/device?action=add", {device: stringify}, function () {

        }, "json")
    },


    addVendor: function (event) {
        this.state.Device.vendor = event.target.value
    },

    addAddress: function (event) {
        this.state.Device.address = event.target.value
    },

    addVersion: function (event) {
        var Dev = this.state.Device;
        Dev.snmp.version = event.target.value;
        this.setState({
            Device: Dev
        });
    },

    addUsername: function (event) {
        this.state.Device.snmp.username = event.target.value
    },

    addTypeHash: function (event) {
        this.state.Device.snmp.hash.typeHash = event.target.value
    },

    addHashPassword: function (event) {
        this.state.Device.snmp.hash.hashPassword = event.target.value

    },

    addTypeEncrypt: function (event) {
        this.state.Device.snmp.encryption.typeEncrypt = event.target.value
    },
    addEncryptPassword: function (event) {
        this.state.Device.snmp.encryption.encryptionPassword = event.target.value
    }


});

ReactDOM.render(
    <Header/>,
    document.getElementById('header')
);
