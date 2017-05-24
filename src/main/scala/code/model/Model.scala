package code.model

import scala.beans.BeanInfo

@BeanInfo
case class Application( customerName : String, creditScore : Int )

@BeanInfo
case class Approval( message : String, approved : Boolean )

