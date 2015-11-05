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
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Brujula3D {

	Cone cone;
	Transform3D transform;
	TransformGroup transformGroup;

	public Brujula3D(Canvas3D canvas) {

		SimpleUniverse universe = new SimpleUniverse(canvas);
		BranchGroup group = new BranchGroup();

		transformGroup = new TransformGroup();
		transform = new Transform3D();

		Cone cone = new Cone(0.1f, 1f);

		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.setTransform(transform);
		transformGroup.addChild(cone);
		group.addChild(transformGroup);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

		Appearance appearance = new Appearance();

		cone.setAppearance(appearance);

		Color3f lightColor = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f lightDirection = new Vector3f(0.0f, 0.0f, -1f);

		/*
		 * Lights
		 */
		DirectionalLight light1 = new DirectionalLight(lightColor, lightDirection);
		light1.setInfluencingBounds(bounds);
		group.addChild(light1);

		AmbientLight ambientLightNode = new AmbientLight(lightColor);
		ambientLightNode.setInfluencingBounds(bounds);
		group.addChild(ambientLightNode);

		Material mat = new Material();
		mat.setAmbientColor(new Color3f(0.0f, 0.0f, 1.0f));
		mat.setDiffuseColor(new Color3f(0.7f, 0.7f, 0.7f));
		mat.setSpecularColor(new Color3f(0.7f, 0.7f, 0.7f));
		appearance.setMaterial(mat);

		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(group);
	}

	public void rotate(DatoMagnetometro magnetometro) {

		this.transform = new Transform3D();
		Transform3D temp = new Transform3D();

//		temp.rotX(Math.atan2(magnetometro.z, magnetometro.y));
//		this.transform.mul(temp);
//
//		temp.rotY(Math.atan2(magnetometro.z, magnetometro.x));
//		this.transform.mul(temp);

		temp.rotZ(Math.atan2(magnetometro.y, magnetometro.x));
		this.transform.mul(temp);

		this.transformGroup.setTransform(transform);
	}

}
