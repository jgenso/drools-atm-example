package code.model

import org.drools.core.event.DebugAgendaEventListener
import org.junit.Assert._
import org.junit.Test
import org.kie.api.KieServices
import org.kie.api.runtime.ClassObjectFilter
import org.kie.api.runtime.rule.{AgendaFilter, Match}
import scala.collection.JavaConverters._

class AtmRulesTest {

  @Test
  def shouldApproveIfRule1IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule2IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = false,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule3IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = true,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule4IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = false,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule5IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 3,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule6IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = -0.20,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule7IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 500.20,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule8IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "dui",
      confirmed = true,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule9IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = false,
      bank = "Banco Union"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule10IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "print",
      confirmed = true,
      bank = "Banco Nacional de Bolivia"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Print approved", approval.message )
    }

  }

  @Test
  def shouldApproveIfRule11IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Nacional de Bolivia"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

    val j = session.getObjects( new ClassObjectFilter( classOf[ Commission ] ) ).iterator( )

    while ( j.hasNext ) {
      val commission = j.next.asInstanceOf[ Commission ]
      assertTrue( 12.0d == commission.amount )
    }

  }

  @Test
  def shouldApproveIfRule12IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Sol"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

    val j = session.getObjects( new ClassObjectFilter( classOf[ Commission ] ) ).iterator( )

    while ( j.hasNext ) {
      val commission = j.next.asInstanceOf[ Commission ]
      assertTrue( 35.0d == commission.amount )
    }

  }

  @Test
  def shouldApproveIfRule13IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Mercantil Santa Cruz"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

    val j = session.getObjects( new ClassObjectFilter( classOf[ Commission ] ) ).iterator( )

    while ( j.hasNext ) {
      val commission = j.next.asInstanceOf[ Commission ]
      assertTrue( 12.0d == commission.amount )
    }

  }

  @Test
  def shouldApproveIfRule14IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Ganadero"
    )

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.fireAllRules( )
    session.dispose( )

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )

    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertTrue( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

    val j = session.getObjects( new ClassObjectFilter( classOf[ Commission ] ) ).iterator( )

    while ( j.hasNext ) {
      val commission = j.next.asInstanceOf[ Commission ]
      assertTrue( 20.0d == commission.amount )
    }

  }

  @Test
  def shouldApproveIfRule15IsApplied( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    val aTMBalance = ATMBalance(0)

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)
    session.insert(aTMBalance)
    session.fireAllRules( new AgendaFilter() {
      def accept(m: Match ): Boolean =
      {
        val rulename = m.getRule().getName()

        if(rulename.equals("Rule 15")) true else false
      }
    })

    val i = session.getObjects( new ClassObjectFilter( classOf[ Approval ] ) ).iterator( )


    while ( i.hasNext ) {
      val approval = i.next.asInstanceOf[ Approval ]
      assertFalse( approval.approved )
      assertEquals( "Withdrawal approved", approval.message )
    }

  }

  @Test
  def shouldUpdateTransaction( ) = {
    val session = KieServices.Factory.get( ).newKieClasspathContainer( ).newKieSession( "atm-ksession" )
    val transaction = Transaction(
      1,
      valid_card = true,
      expired = false,
      pin_correct = true,
      attempts = 1,
      balance_after = 200,
      amount = 400,
      fee = 0,
      transaction_type = "withdraw",
      confirmed = true,
      bank = "Banco Union"
    )

    val transaction2 = transaction.copy(attempts = 3)

    session.addEventListener(new DebugAgendaEventListener)

    session.insert(transaction)

    assertTrue( 1 == session.getFactCount)

    val fhT1 = session.getFactHandle(transaction)

    session.delete(fhT1)

    assertTrue( 0 == session.getFactCount)

    session.insert(transaction2)

    assertTrue( 1 == session.getFactCount)

    val i = session.getObjects( new ClassObjectFilter( classOf[ Transaction] ) ).toArray.toList(0).asInstanceOf[Transaction]

    assertTrue( i.attempts == 3)

  }
}
