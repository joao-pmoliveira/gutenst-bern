import { NextRequest, NextResponse } from "next/server";
import { decrypt } from "./lib/session";
import { cookies } from "next/headers";

export async function middleware(request: NextRequest) {
    const { pathname } = request.nextUrl;

    const publicPaths = ["/login", "/signup"];

    if (publicPaths.includes(pathname)) {
        return NextResponse.next();
    }

    const cookieStore = await cookies();
    const cookie = cookieStore.get("jwt-session");
    const token = cookie?.value;

    const session = await decrypt(token);

    if (!session?.id) {
        return NextResponse.redirect(new URL("/login", request.nextUrl));
    }

    return NextResponse.next();
}

export const config = {
    matcher: ['/((?!api|_next/static|_next/image|favicon.ico|sitemap.xml|robots.txt).*)'],
}
