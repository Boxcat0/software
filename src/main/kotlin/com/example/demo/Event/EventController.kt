package com.example.demo.Event

import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
class EventController {
    @GetMapping("/myPage")
    fun myPage(@AuthenticationPrincipal userDetails: UserDetails,
               model: Model):String{
        model.addAttribute("userName",userDetails.username)
        return "UserPage"
    }
    @GetMapping("/Map2")
    fun openMap2():String{
        return "Map2"
    }
    @GetMapping("/Map")
    fun openMap(session: HttpSession):String {
        session.removeAttribute("GymId")
        session.removeAttribute("GymPosition")
        return "Map"
    }
    @GetMapping("/remap")
    fun openReMap(@AuthenticationPrincipal userDetails: UserDetails,
                  model: Model,session: HttpSession):String{
        session.removeAttribute("GymId")
        session.removeAttribute("GymPosition")
        return "Map"
    }
    @GetMapping("/gym_data")
    fun data():String{
        return "gym_data.json"
    }
    @GetMapping("/map_click")
    fun clickEven(@AuthenticationPrincipal userDetails: UserDetails,
                  model: Model,session: HttpSession
    ):String{
        model.addAttribute("userName",userDetails.username)
        return "map_click"
    }
    @GetMapping("/map_click2")
    fun clickeven():String{
        return "map_click2"
    }

    @PostMapping("/map_click2")
    fun mapclick2event(eventGym: eventGym,model: Model):String{
        model.addAttribute("GymId",eventGym.name)
        return "gymMaster"
    }
    @PostMapping("/map_click")
    fun gotoevent(@AuthenticationPrincipal userDetails: UserDetails,
                  model: Model,eventGym: eventGym,session: HttpSession
    ):String
    {
        if(session.getAttribute("GymId") == null)
        {
            println(eventGym.name)
            println(eventGym.position)
            model.addAttribute("userName",userDetails.username)
            session.setAttribute("GymId",eventGym.name)
            session.setAttribute("GymPosition",eventGym.position)
            model.addAttribute("GymId",eventGym.name)
            model.addAttribute("GymPosition",eventGym.position)
            return "eventPage"
        }
        else{
            println(eventGym.name+" session is here")
            println(eventGym.position)
            model.addAttribute("userName",userDetails.username)
            model.addAttribute("GymId",session.getAttribute("GymId"))
            model.addAttribute("GymPosition",session.getAttribute("GymPosition"))
            return "eventPage"
        }
    }
}