import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.scenegraph.io.UnsupportedUniverseException;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Tracking3D {

	// Transform3D transform;
	TransformGroup transformGroup;
	BranchGroup group;
	private Appearance appearance;
	private SimpleUniverse universe;

	public Tracking3D(Canvas3D canvas) {

		universe = new SimpleUniverse(canvas);
		group = new BranchGroup();

		transformGroup = new TransformGroup();
		// transform = new Transform3D();

		Sphere sphere = new Sphere(0.025f);

		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// transformGroup.setTransform(transform);
		transformGroup.addChild(sphere);
		group.addChild(transformGroup);

		appearance = new Appearance();
		Material mat = new Material();
		mat.setAmbientColor(new Color3f(0.0f, 0.0f, 1.0f));
		mat.setDiffuseColor(new Color3f(0.7f, 0.7f, 0.7f));
		mat.setSpecularColor(new Color3f(0.7f, 0.7f, 0.7f));
		appearance.setMaterial(mat);

		sphere.setAppearance(appearance);

		Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f lightDirection = new Vector3f(0.0f, 0.0f, -1f);

		/*
		 * Lights
		 */
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

		DirectionalLight light1 = new DirectionalLight(lightColor, lightDirection);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);

		AmbientLight ambientLightNode = new AmbientLight(lightColor);
		ambientLightNode.setInfluencingBounds(bounds);
		group.addChild(ambientLightNode);

		universe.addBranchGraph(group);

		universe.getViewingPlatform().setNominalViewingTransform();
	}

	public void moveX(float cantidad) {
		Sphere sphere2 = new Sphere(0.025f);
		sphere2.setAppearance(appearance);
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		Vector3f vector = new Vector3f(0, cantidad, 0);
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(sphere2);
		BranchGroup branch = new BranchGroup();
		branch.addChild(tg);
		universe.addBranchGraph(branch);
	}
	
	public void moveY(float cantidad) {
		Sphere sphere2 = new Sphere(0.025f);
		sphere2.setAppearance(appearance);
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		Vector3f vector = new Vector3f(cantidad, 0, 0);
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(sphere2);
		BranchGroup branch = new BranchGroup();
		branch.addChild(tg);
		universe.addBranchGraph(branch);
	}
	
	public void moveZ(float cantidad) {
		Sphere sphere2 = new Sphere(0.025f);
		sphere2.setAppearance(appearance);
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		Vector3f vector = new Vector3f(0, 0, cantidad);
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(sphere2);
		BranchGroup branch = new BranchGroup();
		branch.addChild(tg);
		universe.addBranchGraph(branch);
	}
	
	public void move(Vector3f vector) {
		Sphere sphere2 = new Sphere(0.025f);
		sphere2.setAppearance(appearance);
		TransformGroup tg = new TransformGroup();
		Transform3D transform = new Transform3D();
		transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(sphere2);
		BranchGroup branch = new BranchGroup();
		branch.addChild(tg);
		universe.addBranchGraph(branch);
	}

}
