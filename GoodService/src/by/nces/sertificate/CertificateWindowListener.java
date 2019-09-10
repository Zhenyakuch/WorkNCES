package by.nces.sertificate;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;

public class CertificateWindowListener implements ShellListener {

	@Override
	public void shellActivated(ShellEvent arg0) {

	}

	@Override
	public void shellClosed(ShellEvent arg0) {
		System.exit(0);
	}

	@Override
	public void shellDeactivated(ShellEvent arg0) {

	}

	@Override
	public void shellDeiconified(ShellEvent arg0) {
	
	}

	@Override
	public void shellIconified(ShellEvent arg0) {

	}

}
