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

    @GetMapping("/Map")
    fun openMap():String {
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
    @PostMapping("/map_click")
    fun gotoevent(@AuthenticationPrincipal userDetails: UserDetails,
                  model: Model,eventGym: eventGym,session: HttpSession
    ):String
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
    /*@GetMapping("/middlePage")
    fun middle(@AuthenticationPrincipal userDetails: UserDetails,
               session: HttpSession,model: Model
    ): String{
        println(session.getAttribute("sessionGym"))
        if(session.getAttribute("sessionGym") != null)//세션 확인 ->뒤로가기 대비
        {
            val userId : String = userDetails.username
            model.addAttribute("GymId", session.getAttribute("sessionGym"))
            model.addAttribute("userName", userId)
            model.addAttribute("GymPosition", session.getAttribute("sessionPosition"))
            return "eventPage"
        }
       else if(session.getAttribute("sessionGym") == null)
        {
            return "middlePage"
        }
        return "middlePage"
    }
    @RequestMapping("/middlePage")
    fun nextEvent(@AuthenticationPrincipal userDetails: UserDetails,
                  model : Model, eventGym: eventGym,
                  session: HttpSession
    ):String{
        val userId : String = userDetails.username
        val targetId : String = eventGym.name
        val targetPosition : String = eventGym.position
        model.addAttribute("GymId",targetId)
        model.addAttribute("userName", userId)
        model.addAttribute("GymPosition",targetPosition)
        session.setAttribute("sessionGym",targetId)//세션에 저장 -> 혹여 파일 이동에 문제생길까봐
        session.setAttribute("sessionPosition",targetPosition)
        return "eventPage"
    }*/
}