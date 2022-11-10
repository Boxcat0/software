const fs = require("fs");
fs.readFile("js2/health.csv","utf-8", (err,data2)=>{
    //console.log(data2)
    let rowToData2 = data2.split("\n");
    let data2Key = rowToData2[0].split(",");
    let userArray = [];
    for(let j = 0; j < rowToData2.length-2; j++){
        const dataObject = {};
        for(let i = 0; i<rowToData2.length-2; i++){
            dataObject[data2Key[i]] = rowToData2[j+1].split(",")[i];
        }
        userArray.push(dataObject)
    }
    console.log(userArray)
    console.log(data2Key)
})