import { createSession } from "@/lib/session";
import { FormState, SignupFormSchema, SignupResponseSchema } from "@/types/formSchemas";
import { redirect } from "next/navigation";

export async function login(state: FormState, formData: FormData) {

    const validatedFields = SignupFormSchema.safeParse({
        username: formData.get("username"),
        password: formData.get("password"),
    })

    if (!validatedFields.success) {
        return {
            errors: validatedFields.error.flatten().fieldErrors,
        }
    }

    const username = validatedFields.data.username;
    const password = validatedFields.data.password;
    const baseURL = process.env.NEXT_PUBLIC_API_URL;

    const res = await fetch(`${baseURL}/api/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            username: username,
            password: password,
        })
    })

    if (!res.ok) {
        const data = await res.json();
        return { message: data.message }
    }

    const data = await res.json();

    const user = {
        id: data["id"] || null,
        username: data["username"] || null,
        roles: data["roles"] || null,
    }

    await createSession(user.id, user.username, user.roles);

    redirect("/");
}

export async function signup(state: FormState, formData: FormData) {
    const validatedFields = SignupFormSchema.safeParse({
        username: formData.get("username"),
        password: formData.get("password"),
    })

    if (!validatedFields.success) {
        return {
            errors: validatedFields.error.flatten().fieldErrors,
        }
    }

    const username = validatedFields.data.username;
    const password = validatedFields.data.password;
    const baseURL = process.env.NEXT_PUBLIC_API_URL;


    const res = await fetch(`${baseURL}/api/auth/signup`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            username: username,
            password: password,
        })
    })

    if (!res.ok) {
        const data = await res.json();
        return { message: data.message }
    }

    const data = await res.json();

    const validatedResponseField = SignupResponseSchema.safeParse({
        id: data.id,
        username: data.username,
        roles: data.roles,
    })

    if (!validatedResponseField.success) {
        return {
            errors: validatedResponseField.error.flatten().fieldErrors,
        }
    }

    const user = {
        id: validatedResponseField.data.id,
        username: validatedResponseField.data.username,
        roles: validatedResponseField.data.roles,
    }

    await createSession(String(user.id), user.username, user.roles);
    redirect("/");

}
