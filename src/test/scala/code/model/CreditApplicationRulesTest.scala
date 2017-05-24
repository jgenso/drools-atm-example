package code.model

import org.junit.Assert._
import org.junit.Test
import org.kie.api.KieServices
import org.kie.api.runtime.ClassObjectFilter

class CreditApplicationRulesTest {

  @Test
  def shouldApproveWithStandardCreditLimit( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "credit-approval-ksession" )
    val application = Application( "michael", 500 )

    session.insert( application )
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "You received the standard credit limit", approval.message )
    }

  }

  @Test
  def shouldApproveWithBetterCreditLimit( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "credit-approval-ksession" )
    val application = Application( "michael", 600 )

    session.insert( application )
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Congratulations!!! You received an extended line of credit", approval.message )
    }
  }

  @Test
  def shouldRejectApplication = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "credit-approval-ksession" )
    val application = Application( "michael", 200 )

    session.insert( application )
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Sorry, you were not approved", approval.message )
    }
  }

}
