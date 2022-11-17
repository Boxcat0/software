const express=require('express')
const fs=require('fs')
const app=express()
const bodyparser=require('body-parser')

app.use(bodyparser.json())
app.use(bodyparser.urlencoded({extended:true}))
app.use(express.static('public'))
//app.set('view engine',ejs)

app.get('/map', (request, response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/Map.html")
//response.sendFile("C:/VSCODE/js2/c.html")
})

app.get('/map_click',(request, response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/map_click.html")
})

app.get('/gym_data',(request,response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/gym_data.json")
})

app.get('/star',(request,response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/Starreview.html")
})

app.get('/star_js',(request,response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/star.js")
})

app.get('/star_css',(request,response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/star.css")
})

app.get('/star_png',(request,response)=>{
    response.sendFile("C:/Users/vv545/IdeaProjects/demo/src/main/resources/templates/img/starrate.png")
})

app.listen(8081,()=>{
    console.log('server Start')
})